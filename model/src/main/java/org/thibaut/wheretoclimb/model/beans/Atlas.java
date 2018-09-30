package org.thibaut.wheretoclimb.model.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Bean used to define a book or a document that contains one or many climbing areas
 */
@Entity
@Table(name="atlas")
@PrimaryKeyJoinColumn(name = "element_id")
public class Atlas extends Element {

//----------ATTRIBUTES----------

	@ManyToMany
	@JoinTable(
		name = "areas_in_atlases",
		joinColumns = { @JoinColumn(name = "atlas_id") },
		inverseJoinColumns = { @JoinColumn(name = "area_id") } )
	private Collection< Area > areas;
	@ManyToMany
	@JoinTable(
			name = "crags_in_atlases",
			joinColumns = { @JoinColumn(name = "atlas_id") },
			inverseJoinColumns = { @JoinColumn(name = "crag_id") } )
	private Collection< Crag > crags;
//	@NotNull
	private String country;
	private String region;
	private String department;
	private boolean available;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;


//----------CONSTRUCTORS----------

	public Atlas( ) {
	}

	public Atlas( String name, String region, Date createDate, Date updateDate,
	              ArrayList< Comment > commentList, boolean available, User user ) {
		super( name, createDate, updateDate, commentList );
		this.available = available;
		this.user = user;
	}


//----------METHODS----------

	@Override
	public String toString( ) {
		return "Atlas{" +
				       "areas=" + areas +
				       ", crags=" + crags +
				       ", available=" + available +
				       '}';
	}


//----------GETTERS & SETTERS----------


	public Collection< Area > getAreas( ) {
		return areas;
	}

	public void setAreas( Collection< Area > areas ) {
		this.areas = areas;
	}

	public Collection< Crag > getCrags( ) {
		return crags;
	}

	public void setCrags( Collection< Crag > crags ) {
		this.crags = crags;
	}

	public String getCountry( ) {
		return country;
	}

	public void setCountry( String country ) {
		this.country = country;
	}

	public String getRegion( ) {
		return region;
	}

	public void setRegion( String region ) {
		this.region = region;
	}

	public String getDepartment( ) {
		return department;
	}

	public void setDepartment( String department ) {
		this.department = department;
	}

	public boolean isAvailable( ) {
		return available;
	}

	public void setAvailable( boolean available ) {
		this.available = available;
	}

	public User getUser( ) {
		return user;
	}

	public void setUser( User user ) {
		this.user = user;
	}
}
