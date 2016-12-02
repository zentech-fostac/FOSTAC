package com.ir.dao;

import com.ir.form.CourseEnrolledUserForm;
import com.ir.form.RegistrationFormTrainer;
import com.ir.model.City;
import com.ir.model.District;
import com.ir.model.KindOfBusiness;
import com.ir.model.ManageTrainingPartner;
import com.ir.model.PersonalInformationTrainer;
import com.ir.model.State;
import com.ir.model.Title;

public interface RegistrationTrainerDAO {

	String register(RegistrationFormTrainer registrationFormTrainer);

	String updatetrainer(RegistrationFormTrainer registrationFormTrainer,int id);

	City getCity(int id);

	State getState(int id);

	District getDistrict(int id);

	Title getTitle(int id);

	ManageTrainingPartner getTP(int id);
//by Rishi
	long basicCourseTrainer(CourseEnrolledUserForm courseEnrolledUserForm, String loginid);

	long advanceTrainerSave(CourseEnrolledUserForm courseEnrolledUserForm, String loginid);

	long specialTrainerSave(CourseEnrolledUserForm courseEnrolledUserForm, String loginid);
	
//by Rishi

}