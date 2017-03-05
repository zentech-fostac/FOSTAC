package com.ir.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.ir.model.CourseName;
/**
 * Servlet implementation class DeleteState
 */

public class SearchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchData() {
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
		System.out.println("passing name   :" + name);
		String[] totalConnected = name.split("&");
		String id,status;
		
		if(totalConnected[0].split("=").length == 1){
			id = "%";
		}else{
			id = (totalConnected[0].split("="))[1];
		}
		if(totalConnected[1].split("=").length == 1){
			status = "%";
		}else{
			status = (totalConnected[1].split("="))[1];
		}
		
		
		String[] statusA  = status.split("%20");
		String cn = "";
		for(int i = 0 ; i < statusA.length ; i++){
			cn = cn + statusA[i] + " ";
		}
		String fcn = cn.substring(0, cn.length()-1);
		System.out.println(fcn.length());
		
		Configuration conf = new Configuration();
		conf.configure("/hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		String sql ="select maa.manageassessmentagencyid  , ld.loginid, maa.assessmentagencyname , maa.websiteurl , (CASE WHEN ld.isActive = 'Y' THEN 'ACTIVE' ELSE 'INACTIVE' END) as currentstatus "+
					" from manageassessmentagency as maa inner join logindetails as ld "+
					" on ld.id = maa.logindetails "+
					" where upper(maa.assessmentagencyname) like '"+fcn.toUpperCase() +"' and ld.loginid like '"+id+"'";
		Query query = session.createSQLQuery(sql);
		List<CourseName> list = query.list();
		System.out.println(list.size());
		session.close();
		String newList = null ;
		if(list.size() > 0 || list != null){
			System.out.println("data selected finally  " );
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
