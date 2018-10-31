package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
@ToString( exclude = {"areas" , "bookingRequests" , "user"})
//@EqualsAndHashCode( callSuper = true )
public class Atlas extends Element {

//----------ATTRIBUTES----------

	@ManyToMany
	@JoinTable(
		name = "areas_in_atlases",
		joinColumns = { @JoinColumn(name = "atlas_id") },
		inverseJoinColumns = { @JoinColumn(name = "area_id") } )
	private List< Area > areas;
//	@NotNull
	private String scale;
	private String country;
	private String region;
	private String department;
	private boolean available;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "atlas", cascade = CascadeType.ALL)
//	@JoinColumn(name = "atlas_id")
	private List<BookingRequest> bookingRequests;


/*//----------CONSTRUCTORS----------


	public Atlas( String name, LocalDateTime createDate, LocalDateTime updateDate,
	              ArrayList< Comment > commentList, boolean available, String country, String scale, String region, User user) {
		super( name, createDate, updateDate, commentList );
		this.available = available;
		this.country = country;
		this.user = user;
		this.region = region;
		this.scale = scale;
	}*/


//----------METHODS----------


}
