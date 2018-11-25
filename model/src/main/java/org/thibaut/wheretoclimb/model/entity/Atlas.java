package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

	@OneToMany(mappedBy = "atlas", cascade=CascadeType.REMOVE)
	private List< Area > areas;

	private String country;

	private String region;

	private String department;

	private boolean available;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "atlas" , cascade=CascadeType.REMOVE)
	private List<BookingRequest> bookingRequests;

}
