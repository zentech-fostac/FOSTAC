package com.ir.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ir.bean.common.IntStringBean;
import com.ir.bean.common.PropertyUtils;
import com.ir.form.LoginForm;
import com.ir.form.ManageCourse;
import com.ir.model.City;
import com.ir.model.CourseName;
import com.ir.model.CourseType;
import com.ir.model.ManageCourseContent;
import com.ir.model.State;
import com.ir.model.Utility;
import com.ir.service.PageLoadService;
import com.zentech.logger.ZLogger;

@Controller
public class MainRedirect {
	
	
	@Autowired
	@Qualifier("pageLoadService")
	PageLoadService pageLoadService;
	
	   @RequestMapping(value="/showTrainingCalendarDetails" , method=RequestMethod.POST)
		@ResponseBody
		public void getApplicationStatusDetails(@RequestBody Utility utility,HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException{
		   new ZLogger("showTrainingCalendarDetails", "Get Application Status............", "MainRedirect.java");
			List<Object[]> list=new ArrayList<>();
			try{
				 list = pageLoadService.loadTrainingDetails(utility);
				 response.setContentType("text/html;charset=UTF-8");
			        PrintWriter out = response.getWriter();
			        out.print(new Gson().toJson(list));
			        out.flush();
			}catch(Exception e){
				e.printStackTrace();
				new ZLogger("showTrainingCalendarDetails", "Exception while showTrainingCalendarDetails "+e.getMessage(), "MainRedirect.java");
			}
			
		}
	   
	   @RequestMapping(value="/calendarSearch" ,method = RequestMethod.GET)
	   public String calendarSearch(@ModelAttribute("utility")Utility utility,HttpSession session,BindingResult result ,  Model model) {
		   try{
			   List<CourseName> courseNameList=pageLoadService.getCouserNameList(utility.getCourseTypeId());
			   List<IntStringBean> mangePartnerList=pageLoadService.getTrainingPartnerList(utility.getCourseTypeId());
			   List<City> citys=pageLoadService.loadCity(0);
			   List<State> states=pageLoadService.loadState();
			   model.addAttribute("courseNameList", new Gson().toJson(courseNameList));
			   model.addAttribute("mangePartnerList", new Gson().toJson(mangePartnerList));
			   model.addAttribute("citys", new Gson().toJson(citys));
			   model.addAttribute("states", new Gson().toJson(states));
			   model.addAttribute("courseTypeId", utility.getCourseTypeId());
			   model.addAttribute("trainingDate", utility.getTrainingDate());
		   }catch(Exception e){
			   e.printStackTrace();
			   new ZLogger("calendarSearch", "Exception while calendarSearch "+e.getMessage(), "MainRedirect.java");
		   }
		   return "calendarSearch";
	   }
	   
	   @RequestMapping(value="/basic-level" ,method = RequestMethod.GET)
	   public String basicLevel(@ModelAttribute("login") LoginForm loginForm,HttpSession session,BindingResult result ,  Model model) {
		   try{
			   List<CourseName> courseNameList=pageLoadService.getCouserNameList(Integer.parseInt(PropertyUtils.basicLevel));
			   List<String> trainingPartnerNameList=pageLoadService.getTrainingPartnerNameList();
			   List<ManageCourseContent> manageCourseContents=pageLoadService.getManageCourseContentList(Integer.parseInt(PropertyUtils.basicLevel));
			 model.addAttribute("courseNameList", new Gson().toJson(courseNameList));
			   model.addAttribute("trainingPartnerNameList", new Gson().toJson(trainingPartnerNameList));
			   model.addAttribute("manageCourseContents", new Gson().toJson(manageCourseContents));
		   }catch(Exception e){
			   e.printStackTrace();
			   new ZLogger("basic-level", "Exception while basic-level "+e.getMessage(), "MainRedirect.java");
		   }
		    return "basic-level";
	   }
	   
	   @RequestMapping(value="/advance-level" ,method = RequestMethod.GET)
	   public String advanceLevel(@ModelAttribute("login") LoginForm loginForm,HttpSession session,BindingResult result ,  Model model) {
		   try{
			   List<CourseName> courseNameList=pageLoadService.getCouserNameList(Integer.parseInt(PropertyUtils.advanceLevel));
			   List<String> trainingPartnerNameList=pageLoadService.getTrainingPartnerNameList();
			   List<ManageCourseContent> manageCourseContents=pageLoadService.getManageCourseContentList(Integer.parseInt(PropertyUtils.advanceLevel));
			   model.addAttribute("courseNameList", new Gson().toJson(courseNameList));
			   model.addAttribute("trainingPartnerNameList", new Gson().toJson(trainingPartnerNameList));
			   model.addAttribute("manageCourseContents", new Gson().toJson(manageCourseContents));
		   }catch(Exception e){
			   e.printStackTrace();
			   new ZLogger("advance-level", "Exception while advance-level "+e.getMessage(), "MainRedirect.java");
		   }
	     return "advance-level";
	   }
	   @RequestMapping(value="/special-level" ,method = RequestMethod.GET)
	   public String specialLevel(@ModelAttribute("login") LoginForm loginForm,HttpSession session,BindingResult result ,  Model model) {
		   try{
			   List<CourseName> courseNameList=pageLoadService.getCouserNameList(Integer.parseInt(PropertyUtils.specialLevel));
			   List<String> trainingPartnerNameList=pageLoadService.getTrainingPartnerNameList();
			   List<ManageCourseContent> manageCourseContents=pageLoadService.getManageCourseContentList(Integer.parseInt(PropertyUtils.specialLevel));
			   model.addAttribute("courseNameList", new Gson().toJson(courseNameList));
			   model.addAttribute("trainingPartnerNameList", new Gson().toJson(trainingPartnerNameList));
			   model.addAttribute("manageCourseContents", new Gson().toJson(manageCourseContents));
		   }catch(Exception e){
			   e.printStackTrace();
			   new ZLogger("special-level", "Exception while special-level "+e.getMessage(), "MainRedirect.java");
		   }
	     return "special-level";
	   }
	   @RequestMapping(value="/fostac" ,method = RequestMethod.GET)
	   public String fostac(HttpSession session) {
		   session.invalidate();
		   return "redirect:index.jsp";
	   }
	   @RequestMapping(value="/help" ,method = RequestMethod.GET)
	   public String help() {
		   return "help";
	   }
	   @RequestMapping(value="/archive" ,method = RequestMethod.GET)
	   public String archive() {
		   return "archive";
	   }
	   @RequestMapping(value="/usefullinks" ,method = RequestMethod.GET)
	   public String usefullinks() {
		   return "usefullinks";
	   }
	   @RequestMapping(value="/contact" ,method = RequestMethod.GET)
	   public String contact() {
		   return "contact";
	   }
	   @RequestMapping(value="/about" ,method = RequestMethod.GET)
	   public String about() {
		   return "about";
	   }
	   @RequestMapping(value="/disclaimer" ,method = RequestMethod.GET)
	   public String disclaimer() {
		   return "disclaimer";
	   }
	   @RequestMapping(value="/feedback" ,method = RequestMethod.GET)
	   public String feedback() {
		   return "feedback";
	   }
	   @RequestMapping(value="/risksolutions" ,method = RequestMethod.GET)
	   public String risksolutions() {
		   return "risksolutions";
	   }
	   @RequestMapping(value="/faq" ,method = RequestMethod.GET)
	   public String faq() {
		   return "faq";
	   }
	   
	   @RequestMapping(value="/trainingManuals" ,method = RequestMethod.GET)
	   public String trainingManuals() {
		   return "trainingManuals";
	   }
	   
	   @RequestMapping(value="/knowYourTrainingPartner" ,method = RequestMethod.GET)
	   public String knowYourTrainingPartner(Model model) {
		   model.addAttribute("manageTrainingPartnerList", this.pageLoadService.manageTrainingPartnerList());
		   model.addAttribute("manageAssessmentAgencyList", this.pageLoadService.manageAssessmentAgencyList());
		   return "knowYourTrainingPartner";
	   }
	   @RequestMapping(value="/basicFoodSafetyCertification" ,method = RequestMethod.GET)
	   public String basicFoodSafetyCertification(@ModelAttribute("login") LoginForm loginForm,HttpSession session,BindingResult result ,  Model model) {
		   List<CourseName> courseNameList=pageLoadService.getCouserNameList(Integer.parseInt(PropertyUtils.basicLevel));
		   List<String> trainingPartnerNameList=pageLoadService.getTrainingPartnerNameList();
		   List<ManageCourseContent> manageCourseContents=pageLoadService.getManageCourseContentList(Integer.parseInt(PropertyUtils.basicLevel));
		   model.addAttribute("courseNameList", new Gson().toJson(courseNameList));
		   model.addAttribute("trainingPartnerNameList", new Gson().toJson(trainingPartnerNameList));
		   model.addAttribute("manageCourseContents", new Gson().toJson(manageCourseContents));
		   return "basicFoodSafetyCertification";
	   }
	   @RequestMapping(value="/certificationProcess" ,method = RequestMethod.GET)
	   public String certificationProcess() {
		   return "certificationProcess";
	   }
	   @RequestMapping(value="/viewEnrolledCourse" ,method = RequestMethod.GET)
	   public String viewEnrolledCourse() {
		   return "viewEnrolledCourse";
	   }

	   @RequestMapping(value="/afterLoginContact" ,method = RequestMethod.GET)
	   public String afterLoginContact() {
		   return "afterLoginContact";
	   }
	  
	   @RequestMapping(value="/trainee" ,method = RequestMethod.GET)
	   public String trainee() {
		   return "trainee";
	   }
	   
	   @RequestMapping(value="/trainer" ,method = RequestMethod.GET)
	   public String trainer() {
		   return "trainer";
	   }
	   @RequestMapping(value="/trainingPartner" ,method = RequestMethod.GET)
	   public String trainingPartner() {
		   return "trainingPartner";
	   }
	   @RequestMapping(value="/assessor" ,method = RequestMethod.GET)
	   public String assessor() {
		   return "assessor";
	   }
	 @RequestMapping(value="/commonCourses" ,method = RequestMethod.GET)
	   public String cateringList(@ModelAttribute("CourseName")CourseName mcc,HttpSession session,BindingResult result ,  Model model,HttpServletRequest request) {
		   int id=Integer.parseInt(request.getParameter("id"));
		 try{
			 if(id==1){
					model.addAttribute("commonList", this.pageLoadService.cateringList());
					model.addAttribute("commonList2", this.pageLoadService.courseCommonList2(id));
		 }
		 else if(id==2){
				model.addAttribute("commonList", this.pageLoadService.manufacturingList());
				model.addAttribute("commonList2", this.pageLoadService.courseCommonList2(id));
		 }
		 else if(id==3){
				model.addAttribute("commonList", this.pageLoadService.transportList());
				model.addAttribute("commonList2", this.pageLoadService.courseCommonList2(id));
		 }
		 else if(id==4){
				model.addAttribute("commonList", this.pageLoadService.retailList());
				model.addAttribute("commonList2", this.pageLoadService.courseCommonList2(id));
		 }
		 else if(id==5){
				model.addAttribute("commonList", this.pageLoadService.basicList());
				model.addAttribute("commonList2", this.pageLoadService.commonList2(1));
		 }
		 else if(id==6){
				model.addAttribute("commonList", this.pageLoadService.advancedList());
				model.addAttribute("commonList2", this.pageLoadService.commonList2(2));
		 }
		 else if(id==7){
				model.addAttribute("commonList", this.pageLoadService.specialList());
				model.addAttribute("commonList2", this.pageLoadService.commonList2(3));
		 }
		 else if(id==8){
				model.addAttribute("commonList", this.pageLoadService.awarenessList());
				model.addAttribute("commonList2", this.pageLoadService.commonList2(4));
		 }
			
		   }catch(Exception e){
			   e.printStackTrace();
			   new ZLogger("basic-level", "Exception while basic-level "+e.getMessage(), "MainRedirect.java");
		   }
		    return "commonCourses";
	   }
}
