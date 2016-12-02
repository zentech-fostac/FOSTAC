package com.ir.dao;

import java.util.List;

import com.ir.form.ChangePasswordForm;
import com.ir.form.ContactTrainee;
import com.ir.form.CourseEnrolledUserForm;
import com.ir.form.RegistrationFormTrainee;
import com.ir.model.AdmitCardForm;
import com.ir.model.City;
import com.ir.model.CourseName;
import com.ir.model.CourseTrainee;
import com.ir.model.District;
import com.ir.model.FeedbackMaster;
import com.ir.model.KindOfBusiness;
import com.ir.model.ManageTrainingPartner;
import com.ir.model.PersonalInformationTrainee;
import com.ir.model.PersonalInformationTrainingPartner;
import com.ir.model.State;
import com.ir.model.Title;
import com.ir.model.TrainingPartner;

public interface TraineeDAO {
	
	public List<CourseName> courseNameList();
	public CourseName getCourseName(int profileId);
	public CourseName getCourseDetails(int loginId);
	public CourseTrainee getCourseTrainingByCourseTypeID(int typeId);

	public List<ManageTrainingPartner> trainingPartnerList();
	public List<FeedbackMaster> getFeedMasterList(int profileId);

	public List<State> trainingCenterStateList();

	public String updateTrainee(RegistrationFormTrainee registrationFormTrainee , Integer ss);

	public boolean changePasswordTraineeSave(ChangePasswordForm changePasswordForm, String id);

	public long basicSave(CourseEnrolledUserForm courseEnrolledUserForm  , String loginid, int personalinformationtraineeid);

	Title getTitle(int id);

	District getDistrict(int id);

	City getCity(int id);

	State getState(int id);

	KindOfBusiness getKid(int id);

	public String contactTraineeSave(ContactTrainee contactTrainee , String id);

//	public boolean changePasswordTraineeSave(ChangePasswordForm changePasswordForm, String id);
	// Rishi
	public long advanceTraineeSave(CourseEnrolledUserForm courseEnrolledUserForm, String loginid,
			int personalinformationtraineeid);

	public long specialTraineeSave(CourseEnrolledUserForm courseEnrolledUserForm, String loginid,
			int personalinformationtraineeid);

	public boolean changePasswordAssesorSave(ChangePasswordForm changePasswordForm, String id);
	public AdmitCardForm generateAdmitCard(int loginId);
}