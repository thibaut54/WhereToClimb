package org.thibaut.wheretoclimb.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Bean used to define a user/climber account
 */
@Entity
@Table(name = "users")
public class User {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String email;
	@OneToMany(mappedBy = "user")
	private Collection< Atlas > atlases;
	@OneToMany/*(mappedBy = "climber", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "user_id")
	private Collection< Comment > comments;
	private String password;
	private String firstName;
	private String lastName;
	private String userName;
	private String gender;
	private boolean enabled;
	private String confirmationToken;
	private boolean emailVisible;
	private LocalDateTime createAccountDate;
	private LocalDateTime dateOfBirth;
	private String gradeMax;
	private String gradeFirstAttempt;
	private String gradeAverage;
	@ManyToMany
	@JoinTable(
			name = "roles_of_users",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "role_id") } )
	private Collection< Role > roles;



//----------CONSTRUCTORS----------

	public User() {
		super();
		this.enabled=false;
	}

	public User( String gender,
	             String firstName,
	             String lastName,
	             String userName,
	             String email,
	             LocalDateTime createAccountDate ) {
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.createAccountDate = createAccountDate;
	}

	public User( String email,
	             String password,
	             String firstName,
	             String lastName,
	             String userName,
	             String gender,
	             boolean emailVisible,
	             LocalDateTime createAccountDate/*,
	             Date dateOfBirth,
	             String gradeMax,
	             String gradeFirstAttempt,
	             String gradeAverage*/ ) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.gender = gender;
		this.enabled = enabled;
		this.emailVisible = emailVisible;
		this.createAccountDate = createAccountDate;
//		this.dateOfBirth = dateOfBirth;
//		this.gradeMax = gradeMax;
//		this.gradeFirstAttempt = gradeFirstAttempt;
//		this.gradeAverage = gradeAverage;
	}


//----------TOSTRING----------

	@Override
	public String toString( ) {
		return "User{" +
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


//----------GETTERS & SETTERS


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

	public boolean isEnabled( ) {
		return enabled;
	}

	public void setEnabled( boolean enabled ) {
		this.enabled = enabled;
	}

	public String getConfirmationToken( ) {
		return confirmationToken;
	}

	public void setConfirmationToken( String confirmationToken ) {
		this.confirmationToken = confirmationToken;
	}

	public boolean isEmailVisible( ) {
		return emailVisible;
	}

	public void setEmailVisible( boolean emailVisible ) {
		this.emailVisible = emailVisible;
	}

	public LocalDateTime getCreateAccountDate( ) {
		return createAccountDate;
	}

	public void setCreateAccountDate( LocalDateTime createAccountDate ) {
		this.createAccountDate = createAccountDate;
	}

	public LocalDateTime getDateOfBirth( ) {
		return dateOfBirth;
	}

	public void setDateOfBirth( LocalDateTime dateOfBirth ) {
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

