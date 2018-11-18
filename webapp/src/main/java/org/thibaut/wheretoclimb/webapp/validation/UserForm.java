package org.thibaut.wheretoclimb.webapp.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.model.entity.*;
import org.thibaut.wheretoclimb.model.entity.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	private LocalDateTime createAccountDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String gradeAverage;
	private List< Role > roles;
	private List< Atlas > atlases;
	private List< Comment > comments;
	private List< BookingRequest > bookingRequests;


//----------CONSTRUCTORS----------


	public UserForm( User user ){
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.gender = user.getGender();
		this.emailVisible = user.isEmailVisible();
		this.createAccountDate = user.getCreateAccountDate();
		this.dateOfBirth = user.getDateOfBirth();
		this.gradeAverage = user.getGradeAverage();
		this.atlases = user.getAtlases();
		this.comments = user.getComments();
		this.bookingRequests = user.getBookingRequests();
	}

}
