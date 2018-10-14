package org.thibaut.wheretoclimb.model.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification_token")
@NoArgsConstructor
public class VerificationToken {

	private static final int EXPIRATION = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String token;
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	private LocalDateTime expiryDate;


//----------CONSTRUCTORS----------



	public VerificationToken( String token, User user, LocalDateTime expiryDate ) {
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
	}


//----------METHODS----------

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}


//----------GETTERS & SETTERS----------

	public static int getEXPIRATION( ) {
		return EXPIRATION;
	}

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getToken( ) {
		return token;
	}

	public void setToken( String token ) {
		this.token = token;
	}

	public User getUser( ) {
		return user;
	}

	public void setUser( User user ) {
		this.user = user;
	}

	public LocalDateTime getExpiryDate( ) {
		return expiryDate;
	}

	public void setExpiryDate( LocalDateTime expiryDate ) {
		this.expiryDate = expiryDate;
	}
}

