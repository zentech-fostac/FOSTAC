package com.ir.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ir.model.City;
import com.ir.model.CourseName;
import com.ir.model.State;
/**
 * Servlet implementation class DeleteState
 */

public class SearchVacancy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchVacancy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		String name = (request.getQueryString());
		String [] n1 = name.split("&");
		
		String courseType,courseName , trainingDate , requiredExp ,noOfVacancy;
		if(n1[0].split("=")[1].equals("0")){
			courseType = "%";
		}else{
			courseType = n1[0].split("=")[1];
		}
		
		if(n1[1].split("=")[1].equals("0")){
			courseName = "%";
		}else{
			courseName = n1[0].split("=")[1];
		}
		
		//////////////////
		if(n1[2].split("=").length == 1){
			trainingDate = "%";
		}else{
			trainingDate = n1[0].split("=")[1];
		}
		
		
		////////////////
		if(n1[3].split("=")[1].equals("0")){
			requiredExp = "%";
		}else{
			requiredExp = n1[0].split("=")[1];
		}
		if(n1[4].split("=")[1].equals("0")){
			noOfVacancy = "%";
		}else{
			noOfVacancy = n1[0].split("=")[1];
		}
		
		
		Configuration conf = new Configuration();
		conf.configure("/hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		String newList=null;
		System.out.println("district 0");
		String sql = "select pvtc.postvacancytrainingcenterid , ct.coursetype , cn.coursename , pvtc.trainingdate , pvtc.requiredexp , pvtc.noofvacancy "+
					" from postvacancytrainingcenter as pvtc "+
					" inner join coursetype as ct on ct.coursetypeid = pvtc.coursetype "+
					" inner join coursename as cn on cn.coursenameid = pvtc.coursename ";

		Query query = session.createSQLQuery(sql);
		List list = query.list();
		System.out.println(list.size());
		session.close();
		if(list.size() > 0 || list != null){
			System.out.println(list);
			Gson g =new Gson();
			newList = g.toJson(list); 
		}
		out.write(newList);
		out.flush();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}