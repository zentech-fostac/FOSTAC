package com.ir.dao.impl;


import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.ir.dao.RegistrationTrainerDAO;
import com.ir.form.CourseEnrolledUserForm;
import com.ir.form.RegistrationFormTrainer;
import com.ir.model.City;
import com.ir.model.CourseEnrolled;
import com.ir.model.CourseEnrolledUser;
import com.ir.model.District;
import com.ir.model.KindOfBusiness;
import com.ir.model.LoginDetails;
import com.ir.model.ManageTrainingPartner;
import com.ir.model.PersonalInformationTrainee;
import com.ir.model.PersonalInformationTrainer;
import com.ir.model.State;
import com.ir.model.Title;
import com.ir.util.EncryptionPasswordANDVerification;
import com.ir.util.PasswordGenerator;
import com.ir.util.SendMail;



@Component("registrationDAO")
public class RegistrationTrainerDAOImpl implements RegistrationTrainerDAO {

@Autowired
@Qualifier("sessionFactory")
private SessionFactory sessionFactory;
@Autowired
@Qualifier("state")
private State state;
@Autowired
@Qualifier("district")
private District district;
@Autowired
@Qualifier("trainingPartnerName")
private ManageTrainingPartner trainingPartnerName;
@Autowired
@Qualifier("city")
private City city;
@Autowired
@Qualifier("title")
private Title title;

	@Override
	public State getState(int id){
		Session s = sessionFactory.openSession();
		State ss = (State)s.load(State.class, id);
		s.close();
		return ss;
	}
	@Override
	public ManageTrainingPartner getTP(int id){
		Session s = sessionFactory.openSession();
		ManageTrainingPartner mtp = (ManageTrainingPartner)s.load(ManageTrainingPartner.class, id);
		s.close();
		return mtp;
	}
	@Override
	public City getCity(int id){
		Session s = sessionFactory.openSession();
		City cc = (City)s.load(City.class, id);
		s.close();
		return cc;
	}
	@Override
	public District getDistrict(int id){
		Session s = sessionFactory.openSession();
		District dd = (District)s.load(District.class, id);
		s.close();
		return dd;
	}
	@Override
	public Title getTitle(int id){
		Session s = sessionFactory.openSession();
		Title tt = (Title)s.load(Title.class, id);
		s.close();
		return tt;
	}
	
	@Override
	public String register(RegistrationFormTrainer registrationFormTrainer) {
		// TODO Auto-generated method stub
				System.out.println("RegistrationDAOImpl [register] begin for registration trainer");
				Integer personalInformationTrainerIdd=null;
				Session session = sessionFactory.openSession();
				Transaction transaction=session.beginTransaction(); 
				
				PasswordGenerator passwordGenerator = new PasswordGenerator(6);
				char[] pass = passwordGenerator.get();
				String passwordString = String.valueOf(pass);
				boolean checkCom=registrationFormTrainer.isCheckPermanent();
				
				String encryprPassword = null;
				try{
					EncryptionPasswordANDVerification encryptionPasswordANDVerification = new EncryptionPasswordANDVerification();
					encryprPassword = encryptionPasswordANDVerification.encryptPass(passwordString);
					
				}catch(NoSuchAlgorithmException e){
					System.out.println( " no such algo exception error catch ");
				}
				State ps = getState(registrationFormTrainer.getTrainingCenterPermanentState()); 
				State cs = getState(registrationFormTrainer.getTrainingCenterCorrespondenceState());
				District pd = getDistrict(registrationFormTrainer.getTrainingCenterPermanentDistrict());
				District cd = getDistrict(registrationFormTrainer.getTrainingCenterCorrespondenceDistrict());
				City pc = getCity(registrationFormTrainer.getTrainingCenterPermanentCity());
				City cc = getCity(registrationFormTrainer.getTrainingCenterCorrespondenceCity());
				Title tt = getTitle(registrationFormTrainer.getTitle());
				ManageTrainingPartner mtp ; 
				if(registrationFormTrainer.getAssociatedTrainingpartnerName() == 0){
					mtp = new ManageTrainingPartner();
					mtp = null;
				}else{
					mtp = getTP(registrationFormTrainer.getAssociatedTrainingpartnerName());
				}
				boolean checkPeramanent=registrationFormTrainer.isCheckPermanent();
				LoginDetails loginDetails = new LoginDetails();
				loginDetails.setLoginId(registrationFormTrainer.getUserId());
				loginDetails.setPassword(passwordString);
				loginDetails.setEncrypted_Password(encryprPassword);
				loginDetails.setProfileId(4);
				loginDetails.setStatus("A");
				
				PersonalInformationTrainer personalInformationTrainer = new PersonalInformationTrainer();
				personalInformationTrainer.setTitle(tt);
				personalInformationTrainer.setAadharNumber(registrationFormTrainer.getAadharNumber());
				personalInformationTrainer.setFirstName(registrationFormTrainer.getFirstName());
				personalInformationTrainer.setLastName(registrationFormTrainer.getLastName());
				personalInformationTrainer.setMiddleName(registrationFormTrainer.getMiddleName());
				personalInformationTrainer.setDOB(registrationFormTrainer.getDOB());
				personalInformationTrainer.setGender(registrationFormTrainer.getGender());
				personalInformationTrainer.setTrainingCenterCorrespondenceLine1(registrationFormTrainer.getTrainingCenterCorrespondenceLine1());
				personalInformationTrainer.setTrainingCenterCorrespondenceLine2(registrationFormTrainer.getTrainingCenterCorrespondenceLine2());
				personalInformationTrainer.setCorrespondencestate(cs);
				personalInformationTrainer.setCorrespondencedistrict(cd);
				personalInformationTrainer.setCorrespondencecity(cc);
				personalInformationTrainer.setFathername(registrationFormTrainer.getFatherName());
				personalInformationTrainer.setTrainingCenterCorrespondencePincode(registrationFormTrainer.getTrainingCenterCorrespondencePincode());
				personalInformationTrainer.setTrainingCenterPermanentEmail(registrationFormTrainer.getTrainingCenterPermanentEmail());
				personalInformationTrainer.setTrainingCenterPermanentMobile(registrationFormTrainer.getTrainingCenterPermanentMobile());
				personalInformationTrainer.setFoodSafetyExpBackground(registrationFormTrainer.getFoodSafetyExpBackground());
				personalInformationTrainer.setTrainingSessionWishToConduct(registrationFormTrainer.getTrainingSessionWishToConduct());
				personalInformationTrainer.setExpInFoodSafefyTimeYear(registrationFormTrainer.getExpInFoodSafefyTimeYear());
				personalInformationTrainer.setExpInFoodSafefyTimeMonth(registrationFormTrainer.getExpInFoodSafefyTimeMonth());
				personalInformationTrainer.setAssociatedWithAnyTrainingPartner(registrationFormTrainer.getAssociatedWithAnyTrainingPartner());
				personalInformationTrainer.setNoOfTrainingSessionConducted(registrationFormTrainer.getNoOfTrainingSessionConducted());
				personalInformationTrainer.setAssociatedTrainingpartnerName(mtp);
				if(checkPeramanent){
					personalInformationTrainer.setTrainingCenterPermanentLine1(registrationFormTrainer.getTrainingCenterCorrespondenceLine1());
					personalInformationTrainer.setTrainingCenterPermanentLine2(registrationFormTrainer.getTrainingCenterCorrespondenceLine2());
					personalInformationTrainer.setPermanentcity(cc);
					personalInformationTrainer.setPermanentdistrict(cd);
					personalInformationTrainer.setPermanentstate(cs);
					personalInformationTrainer.setCheckAddress("true");
					personalInformationTrainer.setTrainingCenterPermanentPincode(registrationFormTrainer.getTrainingCenterCorrespondencePincode());
				}
				else{
				personalInformationTrainer.setTrainingCenterPermanentLine1(registrationFormTrainer.getTrainingCenterPermanentLine1());
				personalInformationTrainer.setTrainingCenterPermanentLine2(registrationFormTrainer.getTrainingCenterPermanentLine2());
				personalInformationTrainer.setPermanentstate(ps);
				personalInformationTrainer.setPermanentdistrict(pd);
				//System.out.println(pd.getDistrictId() + "dist id");
				personalInformationTrainer.setPermanentcity(pc);
				personalInformationTrainer.setCheckAddress("false");
				personalInformationTrainer.setTrainingCenterPermanentPincode(registrationFormTrainer.getTrainingCenterPermanentPincode());
				}
				personalInformationTrainer.setLoginDetails(loginDetails);
				
				System.out.println("person save before save");
				//Session session = sessionFactory.openSession();
				//Transaction transaction=session.beginTransaction(); 
				personalInformationTrainerIdd = (Integer)session.save(personalInformationTrainer);
				System.out.println("RegistrationDAOImpl [register] begin for registration trainee login   :"+ personalInformationTrainer);
				
				
				String BasicCourse = registrationFormTrainer.getBasicCourse1();
				if(BasicCourse.length() > 0){
					String[] BasicCoursesplited = BasicCourse.split(",");
					System.out.println("basic course length   "+ BasicCoursesplited.length);
					if(BasicCoursesplited.length > 0){
						for(int i=0 ; i < BasicCoursesplited.length ; i++){
							CourseEnrolled courseEnrolledBasic = new CourseEnrolled();
							courseEnrolledBasic.setLoginDetails(loginDetails);
							courseEnrolledBasic.setCoursenameid(Integer.parseInt(BasicCoursesplited[i]));
							System.out.println("BasicCoursesplited  "+ BasicCoursesplited[i]);
							Integer courseenrolledbasic = (Integer)session.save(courseEnrolledBasic);
						}
					}
				}
				String AdvanceCourse = registrationFormTrainer.getAdvanceCourse1();
				if(AdvanceCourse.length() > 0){
					String[] AdvanceCoursesplited = AdvanceCourse.split(",");
					System.out.println("advance course length   "+ AdvanceCoursesplited.length);
					if(AdvanceCoursesplited.length > 0){
						for(int i=0 ; i < AdvanceCoursesplited.length ; i++){
							CourseEnrolled courseEnrolledAdvance = new CourseEnrolled();
							courseEnrolledAdvance.setLoginDetails(loginDetails);
							courseEnrolledAdvance.setCoursenameid(Integer.parseInt(AdvanceCoursesplited[i]));
							System.out.println("AdvanceCoursesplited  "+ AdvanceCoursesplited[i]);
							Integer courseenrolledAdvancee = (Integer)session.save(courseEnrolledAdvance);
						}
					}
				}

				String SpecialCourse = registrationFormTrainer.getSpecialCourse1();
				if(AdvanceCourse.length() > 0){
					String[] SpecialCoursesplited = SpecialCourse.split(",");
					System.out.println("SpecialCourse course length   "+ SpecialCoursesplited.length);
					if(SpecialCoursesplited.length > 0){
						for(int i=0 ; i < SpecialCoursesplited.length ; i++){
							CourseEnrolled courseEnrolledSpecial = new CourseEnrolled();
							courseEnrolledSpecial.setLoginDetails(loginDetails);
							courseEnrolledSpecial.setCoursenameid(Integer.parseInt(SpecialCoursesplited[i]));
							System.out.println("SpecialCoursesplited  "+ SpecialCoursesplited[i]);
							Integer courseenrolledSpeciall = (Integer)session.save(courseEnrolledSpecial);
						}
					}
				}
				
				System.out.println("all insert done");
				transaction.commit();
				session.close();
				if(personalInformationTrainerIdd != 0){
					SendMail sendMail = new SendMail();
					sendMail.mailProperty(passwordString, registrationFormTrainer.getTrainingCenterPermanentEmail(), registrationFormTrainer.getFirstName()+ " " + registrationFormTrainer.getLastName());

					return passwordString+"&"+registrationFormTrainer.getUserId();
				}else{
					return passwordString+"&"+registrationFormTrainer.getUserId();
				}
	}
	
	@Override
	public String updatetrainer(RegistrationFormTrainer registrationFormTrainer, int id) {
		State ps = getState(registrationFormTrainer.getTrainingCenterPermanentState()); 
		State cs = getState(registrationFormTrainer.getTrainingCenterCorrespondenceState());
		District pd = getDistrict(registrationFormTrainer.getTrainingCenterPermanentDistrict());
		District cd = getDistrict(registrationFormTrainer.getTrainingCenterCorrespondenceCity());
		City pc = getCity(registrationFormTrainer.getTrainingCenterPermanentCity());
		City cc = getCity(registrationFormTrainer.getTrainingCenterCorrespondenceCity());
		Title tt = getTitle(registrationFormTrainer.getTitle());
		ManageTrainingPartner mtp = getTP(registrationFormTrainer.getAssociatedTrainingpartnerName());
		
		
		Session s=sessionFactory.openSession();
		
		PersonalInformationTrainer personalinformationtrainer= (PersonalInformationTrainer) s.load(PersonalInformationTrainer.class, id);
		System.out.println("id is"+id);
		System.out.println("aadhar si"+registrationFormTrainer.getTrainingCenterCorrespondenceCity());
		//personalinformationtrainer.setAadharNumber(registrationFormTrainer.getAadharNumber());
		personalinformationtrainer.setAdvanceCourse(registrationFormTrainer.getAdvanceCourse1());
		personalinformationtrainer.setAssociatedTrainingpartnerName(mtp);
		personalinformationtrainer.setBasicCourse(registrationFormTrainer.getBasicCourse1());
		//personalinformationtrainer.setDOB(registrationFormTrainer.getDOB());
		personalinformationtrainer.setExpInFoodSafefyTimeMonth(registrationFormTrainer.getExpInFoodSafefyTimeMonth());
		//personalinformationtrainer.setGender(registrationFormTrainer.getGender());
		//personalinformationtrainer.setFirstName(registrationFormTrainer.getFirstName());
		personalinformationtrainer.setFoodSafetyExpBackground(registrationFormTrainer.getFoodSafetyExpBackground());
		//personalinformationtrainer.setMiddleName(registrationFormTrainer.getMiddleName());
		//personalinformationtrainer.setLastName(registrationFormTrainer.getLastName());
		personalinformationtrainer.setNoOfTrainingSessionConducted(registrationFormTrainer.getNoOfTrainingSessionConducted());
		personalinformationtrainer.setSpecialCourse(registrationFormTrainer.getSpecialCourse1());
		personalinformationtrainer.setTitle(tt);
		personalinformationtrainer.setCorrespondencecity(cc);
		personalinformationtrainer.setCorrespondencedistrict(cd);
		personalinformationtrainer.setCorrespondencestate(cs);
		personalinformationtrainer.setFathername(registrationFormTrainer.getFatherName());
		personalinformationtrainer.setTrainingCenterPermanentLine1(registrationFormTrainer.getTrainingCenterPermanentLine1());
		personalinformationtrainer.setTrainingCenterPermanentLine2(registrationFormTrainer.getTrainingCenterPermanentLine2());
		personalinformationtrainer.setTrainingCenterCorrespondenceLine1(registrationFormTrainer.getTrainingCenterCorrespondenceLine1());
		personalinformationtrainer.setTrainingCenterCorrespondenceLine2(registrationFormTrainer.getTrainingCenterCorrespondenceLine2());
		personalinformationtrainer.setPermanentcity(pc);
		personalinformationtrainer.setPermanentdistrict(pd);
		personalinformationtrainer.setPermanentstate(ps);
		personalinformationtrainer.setTrainingCenterPermanentEmail(registrationFormTrainer.getTrainingCenterPermanentEmail());
		personalinformationtrainer.setTrainingCenterPermanentMobile(registrationFormTrainer.getTrainingCenterPermanentMobile());
		personalinformationtrainer.setTrainingSessionWishToConduct(registrationFormTrainer.getTrainingSessionWishToConduct());
		personalinformationtrainer.setAssociatedWithAnyTrainingPartner(registrationFormTrainer.getAssociatedWithAnyTrainingPartner());
		personalinformationtrainer.setTrainingSessionWishToConduct(registrationFormTrainer.getTrainingSessionWishToConduct());
		personalinformationtrainer.setTrainingCenterCorrespondencePincode(registrationFormTrainer.getTrainingCenterCorrespondencePincode());
		personalinformationtrainer.setExpInFoodSafefyTimeYear(registrationFormTrainer.getExpInFoodSafefyTimeYear());
		s.update(personalinformationtrainer);
		s.beginTransaction().commit();
		
		s.close();
		return "updated";
		}

	@Override
	public long basicCourseTrainer(CourseEnrolledUserForm courseEnrolledUserForm, int loginid) {
		
		Session session =sessionFactory.openSession();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		long date = System.currentTimeMillis();
		System.out.println("roll nu  :"+date);
		System.out.println("loginid  :"+loginid);
		System.out.println("personalinformationtraineeid   :"+courseEnrolledUserForm);
		System.out.println("calander id is  "+courseEnrolledUserForm.getTrainingCalendarId());
		
		CourseEnrolledUser ceu = new CourseEnrolledUser();
		ceu.setLoginDetails(loginid);
		ceu.setProfileId(4);
		ceu.setTrainingCalendarId(courseEnrolledUserForm.getTrainingCalendarId());
		ceu.setRollno(date);
//		ceu.setStatus("Pending");
		
		Integer ce = (Integer) session.save(ceu);
		session.beginTransaction().commit();
	/*	if(ce > 0){
			PersonalInformationTrainee   personalInformationTrainee=(PersonalInformationTrainee) session.load(PersonalInformationTrainee.class, personalinformationtraineeid);
			//personalInformationTrainee.setSteps(2);			
			session.update(personalInformationTrainee);
		}
		*/
		session.close();
		return date;
	}
	@Override
	public long advanceTrainerSave(CourseEnrolledUserForm courseEnrolledUserForm, int loginid) {
		Session session =sessionFactory.openSession();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		long date = System.currentTimeMillis();
		System.out.println("roll nu  :"+date);
		System.out.println("loginid  :"+loginid);
		System.out.println("personalinformationtraineeid   :"+courseEnrolledUserForm);
		System.out.println("calander id is  "+courseEnrolledUserForm.getTrainingCalendarId());
		
		CourseEnrolledUser ceu = new CourseEnrolledUser();
		//ceu.setLoginDetails(loginid);
		ceu.setProfileId(4);
		ceu.setTrainingCalendarId(courseEnrolledUserForm.getTrainingCalendarId());
		ceu.setRollno(date);
		//abhay ceu.setStatus("Pending");
		
		Integer ce = (Integer) session.save(ceu);
		session.beginTransaction().commit();
		session.close();
		return date;
	}
	@Override
	public long specialTrainerSave(CourseEnrolledUserForm courseEnrolledUserForm, int loginid) {
		Session session =sessionFactory.openSession();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		long date = System.currentTimeMillis();
		System.out.println("roll nu  :"+date);
		System.out.println("loginid  :"+loginid);
		System.out.println("personalinformationtraineeid   :"+courseEnrolledUserForm);
		System.out.println("calander id is  "+courseEnrolledUserForm.getTrainingCalendarId());
		
		CourseEnrolledUser ceu = new CourseEnrolledUser();
		//ceu.setLoginDetails(loginid);
		ceu.setProfileId(4);
		ceu.setTrainingCalendarId(courseEnrolledUserForm.getTrainingCalendarId());
		ceu.setRollno(date);
		//abhay ceu.setStatus("Pending");
		
		Integer ce = (Integer) session.save(ceu);
		session.beginTransaction().commit();
		session.close();
		return date;
	}
	// Rishi end
	}
	
	

