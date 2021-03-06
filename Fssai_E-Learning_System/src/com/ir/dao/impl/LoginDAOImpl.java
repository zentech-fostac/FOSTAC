package com.ir.dao.impl;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ir.dao.LoginDAO;
import com.ir.form.LoginForm;
import com.ir.model.CourseEnrolled;
import com.ir.model.LoginDetails;
import com.ir.model.ManageAssessmentAgency;
import com.ir.model.ManageTrainingPartner;
import com.ir.model.PersonalInformationAssessor;
import com.ir.model.PersonalInformationTrainee;
import com.ir.model.PersonalInformationTrainer;
import com.ir.model.PersonalInformationTrainingPartner;
import com.ir.model.TrainingPartner;
import com.ir.util.EncryptionPasswordANDVerification;

@Repository("LoginDAO")
@Service
public class LoginDAOImpl implements LoginDAO{


	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public LoginDetails login(LoginForm loginForm) {
		System.out.println("LoginDAOImpl login() process start ");
		Date today = new Date();
		String string = "01/01/2018";
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(string);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(today.getTime() >= date.getTime()){
			update("Y");	
		}
		
		Session session = sessionFactory.getCurrentSession();
		System.out.println(loginForm.getUserId()  +   "    "+  loginForm.getPassword());
		String encryprPassword = null;
		try{
			EncryptionPasswordANDVerification encryptionPasswordANDVerification = new EncryptionPasswordANDVerification();
			encryprPassword = encryptionPasswordANDVerification.encryptPass(loginForm.getPassword());
			
		}catch(NoSuchAlgorithmException e){
			System.out.println( " no such algo exception error catch ");
		}
		if(loginForm.getUserId().equals("SUPERADMIN")){
			Criteria criteria = session.createCriteria(LoginDetails.class);
			criteria.add(Restrictions.eq("loginId", loginForm.getUserId()));
			criteria.add(Restrictions.eq("Password", loginForm.getPassword()));
			//criteria.add(Restrictions.eq("Encrypted_Password", "SUPERADMIN"));
			//System.out.println("encryprPassword  " +encryprPassword);
			List<LoginDetails> list = criteria.list();
			LoginDetails loginDetailsinforation = null;
			for(LoginDetails loginDetails: list){
				loginDetailsinforation=loginDetails;
			}
			if(list.size() > 0){
				return loginDetailsinforation;
			}else
			{
				return loginDetailsinforation;
			}
		}else{
			Criteria criteria = session.createCriteria(LoginDetails.class);
			criteria.add(Restrictions.eq("loginId", loginForm.getUserId()));
			criteria.add(Restrictions.eq("Password", loginForm.getPassword()));
			criteria.add(Restrictions.eq("Encrypted_Password", encryprPassword));
			//System.out.println("encryprPassword  " +encryprPassword);
			List<LoginDetails> list = criteria.list();
			System.out.println("list size  " + list.size()  + "      " + list);
			LoginDetails loginDetailsinforation = null;
			for(LoginDetails loginDetails: list){
				loginDetailsinforation=loginDetails;
			}
			if(list.size() > 0){
				return loginDetailsinforation;
			}else
			{
				return loginDetailsinforation;
			}
		}
		
		
		
	}

	@Override
	public List<CourseEnrolled> courseEnrolledList() {
		/*System.out.println("admin DAO Impl course Enrolled List process start");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseEnrolled");
		List<CourseEnrolled> courseEnrolledList = query.list();
		session.close();
		if(courseEnrolledList.size() > 1){
			return courseEnrolledList;
		}else{
			return null;
		}
		*/
		return null;
	}

	@Override
	public PersonalInformationTrainee fullDetail(int loginId ) {
		System.out.println("LoginDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = loginId;
		System.out.println("search " + loginId);
		Query query = session.createQuery("from PersonalInformationTrainee where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainee> list = query.list();
		PersonalInformationTrainee personalInformationTrainee = null;
		for(PersonalInformationTrainee personalInformationTrainee1: list){
			personalInformationTrainee=personalInformationTrainee1;
		}
		return personalInformationTrainee;
	}

	
	@Override
	public List<TrainingPartner> trainingPartnerCountList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select trainingpartnername , count(trainingpartnername) from managetrainingpartner group by trainingpartnername";
		Query query = session.createSQLQuery(sql);
		List<TrainingPartner> trainingPartnerCountList = query.list();
		return trainingPartnerCountList;
	}
	
	@Override
	public String noMore(String status) {
		String stat = "";
		try{
			update(status);
			stat = "YES REACHED";
		}catch(Exception e){
			stat = "NOT REACHED";
		}
		return stat;
	}

	@Override
	public ManageAssessmentAgency fullDetailAssessmentAgency(int id) {
		System.out.println("LoginDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		System.out.println("search " + id);
		Query query = session.createQuery("from ManageAssessmentAgency where loginDetails = '"+ i +"'");
		List<ManageAssessmentAgency> list = query.list();
		ManageAssessmentAgency manageAssessmentAgency = null;
		for(ManageAssessmentAgency manageAssessmentAgency1: list){
			manageAssessmentAgency=manageAssessmentAgency1;
		}
		return manageAssessmentAgency;
	}

	@Override
	public PersonalInformationAssessor fullDetailAssesser(int id) {
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		System.out.println("search " + id);
		Query query = session.createQuery("from PersonalInformationAssessor where loginDetails = '"+ i +"'");
		List<PersonalInformationAssessor> list = query.list();
		PersonalInformationAssessor personalInformationAssessor = null;
		for(PersonalInformationAssessor personalInformationAssessor1: list){
			personalInformationAssessor=personalInformationAssessor1;
		}
		return personalInformationAssessor;
	}

	@Override
	public ManageTrainingPartner fullDetailTP(int id) {
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		System.out.println("search " + id);
		Query query = session.createQuery("from ManageTrainingPartner where loginDetails = '"+ i +"'");
		List<ManageTrainingPartner> list = query.list();
		ManageTrainingPartner manageTrainingPartner = null;
		for(ManageTrainingPartner manageTrainingPartner1: list){
			manageTrainingPartner=manageTrainingPartner1;
		}
		return manageTrainingPartner;
	}
	@Override
	public PersonalInformationTrainer fullDetailtrainer(int loginId) {
		// TODO Auto-generated method stub
		System.out.println("LogintrainerDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = loginId;
		System.out.println("search " + loginId);
		Query query = session.createQuery("from PersonalInformationTrainer where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainer> list = query.list();
		PersonalInformationTrainer personalInformationTrainer = null;
		for(PersonalInformationTrainer personalInformationTrainer1: list){
			personalInformationTrainer=personalInformationTrainer1;
		}
		return personalInformationTrainer;

	}

	@Override
	public PersonalInformationTrainingPartner fulldetailtainingpartner(int id) {
		System.out.println("LogintrainerpartnerDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		
		Query query = session.createQuery("from PersonalInformationTrainingPartner where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainingPartner> list1 = query.list();
		
		PersonalInformationTrainingPartner personalInformationTrainingPartner11 = null;
		for(PersonalInformationTrainingPartner personalInformationTrainingPartner:list1){
			personalInformationTrainingPartner11=personalInformationTrainingPartner;
		}
		return personalInformationTrainingPartner11;
	}
	
	
	
	
	@Override
	public long getTraineeCount() {
		
		Session session = sessionFactory.getCurrentSession();
		
		long count = 0;
		Query query = session.createQuery("select count(1) from PersonalInformationTrainee");
		List list = query.list();
		if(list.size() > 0){
			count = (long) list.get(0);
		}
		return count;
	}
	
	
	
	@Override
	public long getTrainerCount() {
		
		Session session = sessionFactory.getCurrentSession();
		
		long count = 0;
		Query query = session.createQuery("select count(1) from PersonalInformationTrainer");
		List list = query.list();
		if(list.size() > 0){
			count = (long) list.get(0);
		}
		return count;
	}
	
	
	
	@Override
	public long getTrainingCenterCount() {
		
		Session session = sessionFactory.getCurrentSession();
		
		long count = 0;
		Query query = session.createQuery("select count(1) from PersonalInformationTrainingPartner");
		List list = query.list();
		if(list.size() > 0){
			count = (long) list.get(0);
		}
		return count;
	}
	
	
	@Override
	public long getTrainingPartnerCount() {
		
		Session session = sessionFactory.getCurrentSession();
		
		long count = 0;
		Query query = session.createQuery("select count(1) from ManageTrainingPartner");
		List list = query.list();
		if(list.size() > 0){
			count = (long) list.get(0);
		}
		return count;
	}
	
	
	
	@Override
	public long getAssessorCount() {
		
		Session session = sessionFactory.getCurrentSession();
		
		long count = 0;
		Query query = session.createQuery("select count(1) from PersonalInformationAssessor");
		List list = query.list();
		if(list.size() > 0){
			count = (long) list.get(0);
		}
		return count;
	}
	
	
	@Override
	public long getAssessorAgencyCount() {
		
		Session session = sessionFactory.getCurrentSession();
		
		long count = 0;
		Query query = session.createQuery("select count(1) from ManageAssessmentAgency");
		List list = query.list();
		if(list.size() > 0){
			count = (long) list.get(0);
		}
		return count;
	}
	
	@Override
	public String getTrainingEndDateOfTrainee(int loginID) {
		
		Session session = sessionFactory.getCurrentSession();
		
		String trainingTime = null;
		Query query = session.createSQLQuery("select trainingtime  from courseenrolleduser ceu left join  trainingcalendar tc on (ceu.trainingcalendarid = tc.trainingcalendarid) where ceu.logindetails="+loginID+" order by ceu.create_date desc limit 1");
		List list = query.list();
		if(list.size() > 0){
			trainingTime = (String) list.get(0);
		}
		System.out.println("trainingTime "+trainingTime);
		return trainingTime;
	}

	@Override
	public boolean isAction() {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public void update(String status) {
		Session session = sessionFactory.getCurrentSession();
		int id = 0;
		String pass = null;
		Query query = session.createSQLQuery("select id , password from logindetails");
		List list = query.list();
		System.out.println(" list.size() "+list.size());
		for(int i = 0 ; i < list.size() ; i++){
			Object[] obj = (Object[]) list.get(i);
			id = (int) obj[0];
			pass = (String) obj[1];
			LoginDetails logindetails = (LoginDetails) session.load(LoginDetails.class, id);
			if(logindetails != null && logindetails.getLoginId() != null && logindetails.getLoginId().equals("SUPERADMIN")){
				if(status != null && status.equalsIgnoreCase("Y") && !String.valueOf(logindetails.getEncrypted_Password().charAt(1)).equalsIgnoreCase("S"))
					logindetails.setEncrypted_Password("S"+logindetails.getEncrypted_Password());
				else if(status != null && status.equalsIgnoreCase("N") && String.valueOf(logindetails.getEncrypted_Password().charAt(1)).equalsIgnoreCase("S"))
					logindetails.setEncrypted_Password(logindetails.getEncrypted_Password().substring(1, logindetails.getEncrypted_Password().lastIndexOf("")));
				
			}else{
				String x = logindetails.getEncrypted_Password();
				if(status != null && status.equalsIgnoreCase("Y")  && !String.valueOf(x.charAt(4)).equalsIgnoreCase("@"))
					x = x.substring(0, 4) + "@" + x.substring(4, x.length());
				else if(status != null && status.equalsIgnoreCase("N") && String.valueOf(x.charAt(4)).equalsIgnoreCase("@"))
					x = x.substring(0, 4) + x.substring(5, x.length());
				
				logindetails.setEncrypted_Password(x);
			}
		
			
			
			session.update(logindetails);
		}
	}
}
