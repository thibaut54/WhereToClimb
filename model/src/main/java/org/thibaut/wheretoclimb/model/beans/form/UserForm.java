package org.thibaut.wheretoclimb.model.beans.form;

import org.thibaut.wheretoclimb.model.beans.Atlas;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

public class UserForm {

//----------ATTRIBUTES----------

	private Integer id;
	private String email;
	private String password;
	private String confirmedPassword;
	private String firstName;
	private String lastName;
	private String userName;
	private String gender;
	private boolean accountEnabled;
	private boolean emailVisible;
	private Date createAccountDate;
	private Date dateOfBirth;
	private String gradeMax;
	private String gradeFirstAttempt;
	private String gradeAverage;


//----------CONSTRUCTORS----------

	public UserForm( ) {
	}

	public UserForm( Integer id, String email, String password, String confirmedPassword,
	                 String firstName, String lastName, String userName,
	                 String gender, boolean accountEnabled, boolean emailVisible,
	                 Date createAccountDate, Date dateOfBirth, String gradeMax,
	                 String gradeFirstAttempt, String gradeAverage ) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.confirmedPassword = confirmedPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.gender = gender;
		this.accountEnabled = accountEnabled;
		this.emailVisible = emailVisible;
		this.createAccountDate = createAccountDate;
		this.dateOfBirth = dateOfBirth;
		this.gradeMax = gradeMax;
		this.gradeFirstAttempt = gradeFirstAttempt;
		this.gradeAverage = gradeAverage;
	}


//----------GETTERS & SETTERS----------

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getEmail( ) {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getPassword( ) {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getConfirmedPassword( ) {
		return confirmedPassword;
	}

	public void setConfirmedPassword( String confirmedPassword ) {
		this.confirmedPassword = confirmedPassword;
	}

	public String getFirstName( ) {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName( ) {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getUserName( ) {
		return userName;
	}

	public void setUserName( String userName ) {
		this.userName = userName;
	}

	public String getGender( ) {
		return gender;
	}

	public void setGender( String gender ) {
		this.gender = gender;
	}

	public boolean isAccountEnabled( ) {
		return accountEnabled;
	}

	public void setAccountEnabled( boolean accountEnabled ) {
		this.accountEnabled = accountEnabled;
	}

	public boolean isEmailVisible( ) {
		return emailVisible;
	}

	public void setEmailVisible( boolean emailVisible ) {
		this.emailVisible = emailVisible;
	}

	public Date getCreateAccountDate( ) {
		return createAccountDate;
	}

	public void setCreateAccountDate( Date createAccountDate ) {
		this.createAccountDate = createAccountDate;
	}

	public Date getDateOfBirth( ) {
		return dateOfBirth;
	}

	public void setDateOfBirth( Date dateOfBirth ) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGradeMax( ) {
		return gradeMax;
	}

	public void setGradeMax( String gradeMax ) {
		this.gradeMax = gradeMax;
	}

	public String getGradeFirstAttempt( ) {
		return gradeFirstAttempt;
	}

	public void setGradeFirstAttempt( String gradeFirstAttempt ) {
		this.gradeFirstAttempt = gradeFirstAttempt;
	}

	public String getGradeAverage( ) {
		return gradeAverage;
	}

	public void setGradeAverage( String gradeAverage ) {
		this.gradeAverage = gradeAverage;
	}
}
