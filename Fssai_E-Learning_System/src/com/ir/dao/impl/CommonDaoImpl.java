package com.ir.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ir.dao.CommonDao;
import com.ir.form.ChangePasswordForm;
import com.ir.util.ChangePasswordUtility;
import com.zentech.logger.ZLogger;

@Repository
@Service
public class CommonDaoImpl implements CommonDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	@Qualifier("changePasswordUtility")
	public ChangePasswordUtility changePasswordUtility;
	
	@Override
	public String getCourseTrainingType(String courseNameId){
		Session session = sessionFactory.getCurrentSession();
		String response = "" ;
		String strQuery = "select case "
				+ "when online = 'Online' and classroom='Nil' THEN 'Online' "
				+ "when online = 'Nil' and classroom='Classroom' THEN 'Classroom' "
				+ "when online ='Online' and classroom = 'Classroom' THEN 'Both' END as modeOfTraining "
				+ "from coursename where coursenameid ="+courseNameId;
		try{
			Query query = session.createSQLQuery(strQuery);
			List list = (List)query.list();
			if(list.size() > 0){
				response = (String)list.get(0);	
			}
		}catch(Exception e){
			System.out.println("Exception while retrieving mode of training for course id - " + courseNameId + ":" + e.getMessage());
			response = "error";
		}finally{
		}
		return response;
	}
	
	@Override
	public boolean changePasswordSave(ChangePasswordForm changePasswordForm, String id) {
		String oldPassword=	changePasswordForm.getOldPassword();
		String newPassword=changePasswordForm.getNewPassword();
		boolean confirm = changePasswordUtility.changePasswordUtil(oldPassword, newPassword, id);
		return confirm;
	}
	
	
	@Override
	public String checkAadhar(String aadhar ,String tableName){
		Session session = sessionFactory.getCurrentSession();
		String response = null;
		String sql="select * from "+tableName+" where aadharnumber = '" + aadhar + "'";
		try{
			Query query = session.createSQLQuery(sql);
			List list = (List)query.list();
			if(list.size() > 0){
				new ZLogger("checkAadhar", "not available to use", "CommonDaoImpl.java");
				response ="Already";
			}else{
				response ="N";
			}
		}catch(Exception e){
			
		}finally{
		}
		return response;
	}
	

	@Override
	public  List getCourseName(String courseName){
		Session session = sessionFactory.getCurrentSession();
		String sql = "select cn.coursenameid , cn.coursecode , cn.coursename from coursename as cn inner join coursetype as ct on ct.coursetypeid = cn.coursetypeid where cn.coursetypeid  = '"+courseName+"'";		
		Query query = session.createSQLQuery(sql);
		List courseTypeList = query.list();
		return courseTypeList;
	}
	
	//getAssessorName
	
	
	
	@Override
	public  List getAssessorName(String assessorAgencyName){
		Session session = sessionFactory.getCurrentSession();
		String sql = "select personalinformationassessorid , (firstname || ' ' ||  middlename || ' ' ||  lastname)  from personalInformationAssessor  where assessmentagencyname  = '"+assessorAgencyName +"'";		
		Query query = session.createSQLQuery(sql);
		List courseTypeList = query.list();
		return courseTypeList;
	}
	
	//getCourseTrainingMode
	
	@Override
	public String getCourseTrainingMode(String courseName ){
		Session session = sessionFactory.getCurrentSession();
		String response = null;
		String sql = "select online from coursename where coursenameid ="+courseName;
		
		try{
			Query query = session.createSQLQuery(sql);
			List list = (List)query.list();
			if(list.size()>0){
				response = (String)list.get(0);	
			}
		}catch(Exception e){
			
		}finally{
		}
		return response;
	}
	
	//	//loadTrainingCenter
	@Override
	public  List loadTrainingCenter(String data){
		Session session = sessionFactory.getCurrentSession();

		String sql="select pitp.personalinformationtrainingpartnerid , pitp.trainingcentrename "+
				" from personalinformationtrainingpartner as pitp "+
				" inner join managetrainingpartner as mtp on pitp.trainingpartnername = mtp.managetrainingpartnerid "+
				" inner join logindetails as ld on ld.id = pitp.logindetails "+
				"  inner join state as s on s.stateid = pitp.trainingpartnerpermanentstate"+
				" where pitp.trainingpartnername = '"+data+"'" ;		
		Query query = session.createSQLQuery(sql);
		List trainingCenterList = query.list();
		return trainingCenterList;
	}	
	@Override
	public  String getTrainingCalendarInfo(String data){
		Session session = sessionFactory.getCurrentSession();
		String result= null;
		String sql=" select cn.coursename || '|' || tp.trainingcentrename  from trainingcalendar tc left join coursename cn on (tc.coursename = cn.coursenameid) left join personalinformationtrainingpartner tp on  (tc.trainingcenter = tp.personalinformationtrainingpartnerid) where to_date(tc.trainingdate , 'DD-MM-YYYY')  = '"+data+"' limit 1";		
		Query query = session.createSQLQuery(sql);
		List lst = query.list();
		if(lst.size() > 0 ){
			
		result =  (String) lst.get(0);	
		}
		return result;
	}
	
	@Override
	public  String getTrainingCenterAddress(String data){
		Session session = sessionFactory.getCurrentSession();
		String result= null;
		String sql="select trainingcentrename || '|' ||  trainingpartnerpermanentline1 || '|' || trainingpartnerpermanentline2 || '|' || districtname || ' , ' || cityname || '|' || statename  || ' - ' || trainingpartnerpermanentpincode  from personalinformationtrainingpartner tp left join state st on (tp.trainingpartnerpermanentstate = st.stateid ) left join district dt on (tp.trainingpartnerpermanentdistrict = dt.districtid ) left join city ct on (tp.trainingpartnerpermanentcity = ct.cityid ) where trainingpartnerpermanentpincode ='"+data+"' limit 1";		
		Query query = session.createSQLQuery(sql);
		List lst = query.list();
		if(lst.size() > 0 ){
			
			result =  (String) lst.get(0);	
		}
		return result;
	}	

}