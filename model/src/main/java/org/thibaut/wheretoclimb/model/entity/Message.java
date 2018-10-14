package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Bean used to define a message that a user can send to any other user of the site
 */
@Entity
@Table(name = "message")
@PrimaryKeyJoinColumn(name = "com_id")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Message extends Communication{

//----------ATTRIBUTES----------

	@ManyToOne
	@JoinColumn(name = "user_recipient_id")
	private User userRecipient;


//----------GETTERS & SETTERS----------

//	public User getUserRecipient( ) {
//		return user;
//	}
//
//	public void setUserRecipient( User user ) {
//		this.user = user;
//	}
}
