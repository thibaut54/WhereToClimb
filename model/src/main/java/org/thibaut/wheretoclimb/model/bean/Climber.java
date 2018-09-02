package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Bean used to define a user/climber account
 */
@Entity
@Table(name = "climber")
public class Climber implements Serializable {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany/*(mappedBy = "climber", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "climberId")
	private Collection< Atlas > atlases;
	@OneToMany/*(mappedBy = "climber", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "climberId")
	private Collection< Comment > comments;
	private String gender;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private boolean isEmailVisible;
	private String gradeFirstAttempt;
	private String gradeMax;
	private String gradeAverage;
	private Date createAccountDate;
	private Date dateOfBirth;


//----------CONSTRUCTORS----------

	public Climber( ) {

	}

	public Climber( ArrayList< Atlas > atlases, ArrayList< Comment > comments,
	                String gender, String firstName, String lastName,
	                String userName, String password, String email,
	                boolean isEmailVisible, String gradeFirstAttempt, String gradeMax,
	                String gradeAverage, Date createAccountDate, Date dateOfBirth ) {
		this.atlases = atlases;
		this.comments = comments;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.isEmailVisible = isEmailVisible;
		this.gradeFirstAttempt = gradeFirstAttempt;
		this.gradeMax = gradeMax;
		this.gradeAverage = gradeAverage;
		this.createAccountDate = createAccountDate;
		this.dateOfBirth = dateOfBirth;
	}

	public Climber( String gender, String firstName, String lastName, String userName, String password, String email, boolean isEmailVisible, String gradeFirstAttempt, String gradeMax, String gradeAverage, Date createAccountDate, Date dateOfBirth ) {
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.isEmailVisible = isEmailVisible;
		this.gradeFirstAttempt = gradeFirstAttempt;
		this.gradeMax = gradeMax;
		this.gradeAverage = gradeAverage;
		this.createAccountDate = createAccountDate;
		this.dateOfBirth = dateOfBirth;
	}

	//	public Climber( String gender, String firstName, String lastName,
//	                String userName, String password, String email, boolean isEmailVisible,
//	                String gradeFirstAttempt, String gradeMax, String gradeAverage,
//	                Date createAccountDate, Date dateOfBirth ) {
//		this.gender = gender;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.userName = userName;
//		this.password = password;
//		this.email = email;
//		this.isEmailVisible = isEmailVisible;
//		this.gradeFirstAttempt = gradeFirstAttempt;
//		this.gradeMax = gradeMax;
//		this.gradeAverage = gradeAverage;
//		this.createAccountDate = createAccountDate;
//		this.dateOfBirth = dateOfBirth;
//	}


//----------TOSTRING----------

	@Override
	public String toString( ) {
		return "Climber{" +
				       "id=" + id +
				       ", gender='" + gender + '\'' +
				       ", firstName='" + firstName + '\'' +
				       ", lastName='" + lastName + '\'' +
				       ", userName='" + userName + '\'' +
				       ", password='" + password + '\'' +
				       ", email='" + email + '\'' +
				       ", createAccountDate='" + createAccountDate + '\'' +
				       '}';
	}


//----------GETTERS & SETTERS----------

	public Integer getId( ) {
		return id;
	}

	private void setId( Integer id ) {
		this.id = id;
	}

	public Collection< Atlas > getAtlases( ) {
		return atlases;
	}

	public void setAtlases( ArrayList< Atlas > atlases ) {
		this.atlases = atlases;
	}

	public String getGender( ) {
		return gender;
	}

	public void setGender( String gender ) {
		this.gender = gender;
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

	public String getPassword( ) {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getEmail( ) {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public Collection< Comment > getComments( ) {
		return comments;
	}

	public void setComments( ArrayList< Comment > comments ) {
		this.comments = comments;
	}

	public boolean isEmailVisible( ) {
		return isEmailVisible;
	}

	public void setEmailVisible( boolean emailVisible ) {
		isEmailVisible = emailVisible;
	}

	public String getGradeFirstAttempt( ) {
		return gradeFirstAttempt;
	}

	public void setGradeFirstAttempt( String gradeFirstAttempt ) {
		this.gradeFirstAttempt = gradeFirstAttempt;
	}

	public String getGradeMax( ) {
		return gradeMax;
	}

	public void setGradeMax( String gradeMax ) {
		this.gradeMax = gradeMax;
	}

	public String getGradeAverage( ) {
		return gradeAverage;
	}

	public void setGradeAverage( String gradeAverage ) {
		this.gradeAverage = gradeAverage;
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
}

