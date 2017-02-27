package com.ir.service.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ir.bean.common.IntStringBean;
import com.ir.bean.common.StringStringBean;
import com.ir.dao.TrainingPartnerDao;
import com.ir.form.ChangePasswordForm;
import com.ir.form.PostVacancyTrainingCenterForm;
import com.ir.form.TrainingCalendarForm;
import com.ir.form.trainingPartner.TrainingPartnerSearch;
import com.ir.model.CourseName;
import com.ir.model.CourseType;
import com.ir.model.ManageAssessmentAgency;
import com.ir.model.PersonalInformationTrainingPartner;
import com.ir.model.PostVacancyTrainingCenter;
import com.ir.model.PostVacancyTrainingCenterBean;
import com.ir.model.Utility;
import com.ir.service.TrainingPartnerService;

@Service
public class TrainingPartnerServiceImpl implements TrainingPartnerService  {

	@Autowired
	@Qualifier("trainingPartnerDAO")
	TrainingPartnerDao trainingPartnerDAO; 

	@Override
	@Transactional
	public List<PersonalInformationTrainingPartner> trainingCenterList() {
		List<PersonalInformationTrainingPartner> trainingCenterList = trainingPartnerDAO.trainingCenterList();
		return trainingCenterList;
	}

	@Override
	@Transactional
	public List<CourseType> courseTypeList() {
		List<CourseType> courseTypeList = trainingPartnerDAO.courseTypeList();
		return courseTypeList;
	}

	@Override
	@Transactional
	public String postVacancyTrainingPartner(PostVacancyTrainingCenterForm postVacancyTrainingCenterForm) {
		String postVacancyTrainingPartner = trainingPartnerDAO.postVacancyTrainingPartner(postVacancyTrainingCenterForm);
		return postVacancyTrainingPartner;
	}

	@Override
	@Transactional
	public boolean changePasswordTrainingPartnerSave(ChangePasswordForm changePasswordForm, String id) {
		boolean changePasswordTraineeSave = trainingPartnerDAO.changePasswordTrainingPartnerSave(changePasswordForm , id);
		return changePasswordTraineeSave;
	}

	@Override
	@Transactional
	public List<CourseType> courseTypes() {
		System.out.println("TrainingPartnerServiceImpl");
		List<CourseType> courseTypeList = trainingPartnerDAO.courseTypes();
		return courseTypeList;
	}
	@Override
	@Transactional
	public List<IntStringBean> getTrainerList() {
		System.out.println("TrainingPartnerServiceImpl");
		List<IntStringBean> trainerList = trainingPartnerDAO.getTrainerList();
		return trainerList;
	}
	@Override
	@Transactional
	public List<IntStringBean> getTraineeList() {
		System.out.println("TrainingPartnerServiceImpl");
		List<IntStringBean> traineeList = trainingPartnerDAO.getTraineeList();
		return traineeList;
	}
	@Override
	@Transactional
	public List<IntStringBean> getTrainingCenterList(Integer userId,Integer profileId) {
		System.out.println("TrainingPartnerServiceImpl");
		List<IntStringBean> trainingCenterList = trainingPartnerDAO.getTrainingCenterList(userId,profileId);
		return trainingCenterList;
	}
	//getTrainingCenter
	@Override
	@Transactional
	public int getTrainingCenter(Integer userId,Integer profileId) {
		System.out.println("TrainingPartnerServiceImpl");
		int trainingCenter = trainingPartnerDAO.getTrainingCenter(userId,profileId);
		return trainingCenter;
	}
	@Override
	@Transactional
	public List<IntStringBean> getAssessorList() {
		System.out.println("TrainingPartnerServiceImpl");
		List<IntStringBean> assessorList = trainingPartnerDAO.getAssessorList();
		return assessorList;
	}
	@Override
	@Transactional
	public Utility editApplicationStatus(PostVacancyTrainingCenterBean postVacancyTrainingCenterBean){
		return trainingPartnerDAO.editApplicationStatus(postVacancyTrainingCenterBean);
	}
	@Override
	@Transactional
	public List<PostVacancyTrainingCenterBean> getTrainingCalenderList(PostVacancyTrainingCenterBean postVacancyTrainingCenterBean){
		return trainingPartnerDAO.getTrainingCalenderList(postVacancyTrainingCenterBean);
	}
	
	@Override
	@Transactional
	public List<StringStringBean> getStatusList() {
		System.out.println("TrainingPartnerServiceImpl");
		List<StringStringBean> statusList = trainingPartnerDAO.getStatusList();
		return statusList;
	}
	@Override
	@Transactional
	public List<StringStringBean> getModeOfTrainingList() {
		System.out.println("TrainingPartnerServiceImpl");
		List<StringStringBean> modeoftraininglist = trainingPartnerDAO.getModeOfTrainingList();
		return modeoftraininglist;
	}
	@Override
	@Transactional
	public List<CourseName> getCourseNameList(){
		return  trainingPartnerDAO.getCourseNameList();
	}

	@Override
	@Transactional
	public List<PostVacancyTrainingCenter> getPostVacancyTrainingList(Integer userId) {
		return  trainingPartnerDAO.getPostVacancyTrainingList(userId);
	}

	@Override
	@Transactional
	public int saveVacancy(PostVacancyTrainingCenterBean postVacancyTrainingCenterBean,Integer profileID, Integer userId) {
		return  trainingPartnerDAO.saveVacancy(postVacancyTrainingCenterBean,profileID,userId);
	}

	@Override
	@Transactional
	public List<PostVacancyTrainingCenter> getAppliedCount(PostVacancyTrainingCenterBean postVacancyTrainingCenterBean) {
		return trainingPartnerDAO.getAppliedCount(postVacancyTrainingCenterBean);
	}

	@Override
	@Transactional
	public void updateApplicationStatusForEnrolledVacancy(PostVacancyTrainingCenterBean postVacancyTrainingCenterBean) {
		trainingPartnerDAO.updateApplicationStatusForEnrolledVacancy(postVacancyTrainingCenterBean);
		
	}

	@Override
	@Transactional
	public PostVacancyTrainingCenterBean getApplicationStatusBean(String loginId,int coursename, int cousertype) {
		return trainingPartnerDAO.getApplicationStatusBean(loginId,coursename, cousertype);
	}

	@Override
	@Transactional
	public void updateUpcomingTrainingsStatus(int id) {
		trainingPartnerDAO.updateUpcomingTrainingsStatus(id);
	}
	@Override
	@Transactional
	public List<TrainingPartnerSearch> getTrainingPartnerDetails(int trainingPartnerId){
		return trainingPartnerDAO.getTrainingPartnerDetails(trainingPartnerId);
	}
	@Override
	@Transactional
	public String trainingCalendarForm(TrainingCalendarForm trainingCalendarForm) {
		String trainingCalendar = trainingPartnerDAO.trainingCalendarForm(trainingCalendarForm);
		return trainingCalendar;
	}
	
	@Override
	@Transactional
	public void setTrainingCalanderDeatils(TrainingCalendarForm trainingCalendarForm , String loginId) {
		trainingPartnerDAO.setTrainingCalanderDeatils(trainingCalendarForm , loginId);
	}
	
	
	@Override
	@Transactional
	public void cancelTrainingCalendar(int id) {
		trainingPartnerDAO.cancelTrainingCalndar(id);
	}
	
	

	@Override
	@Transactional
	public List<IntStringBean> loadAssessmentAgency() {
		List<IntStringBean> loadAssessmentAgency = trainingPartnerDAO.loadAssessmentAgency();
		return loadAssessmentAgency;
	}
	
	//getBatchCodeList
	
	@Override
	public List<String> getBatchCodeList(int courseCode) {
		List<String> loadBatchCode = trainingPartnerDAO.getBatchCodeList(courseCode);
		return loadBatchCode;
	}
	//getCertificateIdList
	
	@Override
	public List<String> getCertificateIdList(String batchcode) {
		List<String> loadCertificate = trainingPartnerDAO.getCertificateIdList(batchcode);
		return loadCertificate;
	}
}
