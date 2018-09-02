package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Bean used to define a book or a document that contains one or many climbing areas
 */
@Entity
@Table(name="atlas")
@PrimaryKeyJoinColumn(name = "elementId")
public class Atlas extends Element{

//----------ATTRIBUTES----------

//	@ManyToOne
//	@JoinColumn(name = "climberId")
//	private Climber climber;
	@ManyToMany
	@JoinTable(
		name = "areasInAtlas",
		joinColumns = { @JoinColumn(name = "atlasId") },
		inverseJoinColumns = { @JoinColumn(name = "areaId") } )
	private Collection< Area > areas;
	@ManyToMany
	@JoinTable(
			name = "cragsInAtlas",
			joinColumns = { @JoinColumn(name = "atlasId") },
			inverseJoinColumns = { @JoinColumn(name = "cragId") } )
	private Collection< Crag > crags;
	private boolean available;


//----------CONSTRUCTORS----------

	public Atlas( ) {
	}

	public Atlas( String name, Date createDate, Date updateDate,
	              ArrayList< Comment > commentList, boolean available ) {
		super( name, createDate, updateDate, commentList );
		this.available = available;
	}


//----------GETTERS & SETTERS----------

	public Collection< Area > getAreas( ) {
		return areas;
	}

	public void setAreas( ArrayList< Area > areas ) {
		this.areas = areas;
	}

	public boolean isAvailable( ) {
		return available;
	}

	public void setAvailable( boolean available ) {
		this.available = available;
	}
}
