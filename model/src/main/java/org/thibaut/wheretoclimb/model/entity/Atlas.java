package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Bean used to define a book or a document that contains one or many climbing areas
 */
@Entity
@Table(name="atlas")
@PrimaryKeyJoinColumn(name = "element_id")
@Getter
@Setter
@NoArgsConstructor
//@EqualsAndHashCode( callSuper = true )
public class Atlas extends Element {

//----------ATTRIBUTES----------

	@OneToMany(mappedBy = "atlas")
	private List< Area > areas;

//	@NotNull
//	@Size(min=3, max=50)
	private String country;

//	@NotNull
//	@Size(min=3, max=50)
	private String region;

//	@Size(min=3, max=50)
	private String department;

//	@NotNull
	private boolean available;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "atlas")
	private List<BookingRequest> bookingRequests;

}
