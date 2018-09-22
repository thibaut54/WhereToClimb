package org.thibaut.wheretoclimb.webapp.validation;

import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.model.beans.Role;

import java.util.Collection;
import java.util.Date;

@Component
public class UserForm {

//----------ATTRIBUTES----------

	private Integer id;
	private String email;
	private String password;
	private String confirmPassword;
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
	private Collection< Role > roles;


//----------CONSTRUCTORS----------

	public UserForm( ) {
	}

	public UserForm( Integer id,
	                 String email,
	                 String password,
	                 String confirmPassword,
	                 String firstName,
	                 String lastName,
	                 String userName,
	                 String gender,
	                 boolean accountEnabled,
	                 boolean emailVisible,
	                 Date createAccountDate,
	                 Date dateOfBirth,
	                 String gradeMax,
	                 String gradeFirstAttempt,
	                 String gradeAverage,
	                 Collection< Role > roles ) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
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
		this.roles = roles;
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

	public String getConfirmPassword( ) {
		return confirmPassword;
	}

	public void setConfirmPassword( String confirmPassword ) {
		this.confirmPassword = confirmPassword;
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

	public Collection< Role > getRoles( ) {
		return roles;
	}

	public void setRoles( Collection< Role > roles ) {
		this.roles = roles;
	}
}
