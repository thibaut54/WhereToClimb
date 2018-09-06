package org.thibaut.wheretoclimb.model.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Bean used to define a user/climber account
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	@OneToMany/*(mappedBy = "climber", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "user_id")
//	@JoinColumn(name = "user_email")
	private Collection< Atlas > atlases;
	@OneToMany/*(mappedBy = "climber", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "user_id")
//	@JoinColumn(name = "user_email")
	private Collection< Comment > comments;
	private String password;
	private String firstName;
	private String lastName;
	private String userName;
	private String gender;
	private boolean emailVisible;
	private Date createAccountDate;
	private Date dateOfBirth;
	private String gradeMax;
	private String gradeFirstAttempt;
	private String gradeAverage;
//	@ManyToMany
////	@OneToMany
//	@JoinTable(
//			name = "roles_of_users",
//			joinColumns = { @JoinColumn(name = "user_id") },
//			inverseJoinColumns = { @JoinColumn(name = "role_id") } )
//	private Collection<Role> roles;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;


//----------CONSTRUCTORS----------

	public User( ) {

	}

	public User( ArrayList< Atlas > atlases, ArrayList< Comment > comments,
	             String gender, String firstName, String lastName,
	             String userName, String password, String email,
	             boolean emailVisible, String gradeFirstAttempt, String gradeMax,
	             String gradeAverage, Date createAccountDate, Date dateOfBirth ) {
		this.atlases = atlases;
		this.comments = comments;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.emailVisible = emailVisible;
		this.gradeFirstAttempt = gradeFirstAttempt;
		this.gradeMax = gradeMax;
		this.gradeAverage = gradeAverage;
		this.createAccountDate = createAccountDate;
		this.dateOfBirth = dateOfBirth;
	}

	public User( String gender, String firstName, String lastName, String userName, String password, String email, boolean emailVisible, String gradeFirstAttempt, String gradeMax, String gradeAverage, Date createAccountDate, Date dateOfBirth ) {
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.emailVisible = emailVisible;
		this.gradeFirstAttempt = gradeFirstAttempt;
		this.gradeMax = gradeMax;
		this.gradeAverage = gradeAverage;
		this.createAccountDate = createAccountDate;
		this.dateOfBirth = dateOfBirth;
	}

	public User( String gender, String firstName, String lastName,
	             String userName, String password, String email, Date createAccountDate ) {
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.createAccountDate = createAccountDate;
	}

	public User( User user ) {
		this.gender = user.getGender();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.createAccountDate = user.getCreateAccountDate();
	}


//----------TOSTRING----------

	@Override
	public String toString( ) {
		return "User{" +
//				       "id=" + id +
				       ", gender='" + gender + '\'' +
				       ", firstName='" + firstName + '\'' +
				       ", lastName='" + lastName + '\'' +
				       ", userName='" + userName + '\'' +
				       ", password='" + password + '\'' +
				       ", email='" + email + '\'' +
				       ", createAccountDate='" + createAccountDate + '\'' +
				       '}';
	}


//----------GETTERS & SETTERS

//	public Integer getId( ) {
//		return id;
//	}
//
//	public void setId( Integer id ) {
//		this.id = id;
//	}

	public Collection< Atlas > getAtlases( ) {
		return atlases;
	}

	public void setAtlases( Collection< Atlas > atlases ) {
		this.atlases = atlases;
	}

	public Collection< Comment > getComments( ) {
		return comments;
	}

	public void setComments( Collection< Comment > comments ) {
		this.comments = comments;
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

	public Role getRole( ) {
		return role;
	}

	public void setRole( Role role ) {
		this.role = role;
	}

	//	public Collection< Role > getRoles( ) {
//		return roles;
//	}
//
//	public void setRoles( Collection< Role > roles ) {
//		this.roles = roles;
//	}
}

