package org.thibaut.wheretoclimb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private List< Atlas > atlases;

	@OneToMany(mappedBy = "user")
	private List< Comment > comments;

	@ManyToMany
	@JoinTable(
			name = "roles_of_users",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "role_id") } )
	private List< Role > roles;

	@OneToMany(mappedBy = "user")
	private List<BookingRequest> bookingRequests;

	private String password;
	private String firstName;
	private String lastName;
	private String userName;
	private String gender;
	private boolean emailVisible;
	private LocalDateTime createAccountDate;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dateOfBirth;
	private String gradeAverage;

//----------CONSTRUCTORS----------

	public User() {
		super();
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

