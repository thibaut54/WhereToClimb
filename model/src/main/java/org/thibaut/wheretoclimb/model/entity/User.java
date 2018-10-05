package org.thibaut.wheretoclimb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Bean used to define a user/climber account
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
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

}

