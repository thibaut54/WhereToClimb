package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="element")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Element {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Date createDate;
	private Date updateDate;
	@OneToMany/*(mappedBy = "element", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "elementId")
	private Collection< Comment > comments;


//----------CONSTRUCTORS----------

	public Element( ) {
	}

	public Element( String name, Date createDate, Date updateDate,
	                ArrayList< Comment > comments ) {
		this.name = name;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.comments = comments;
	}


//----------GETTERS & SETTERS----------

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getName( ) {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Date getCreateDate( ) {
		return createDate;
	}

	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
	}

	public Date getUpdateDate( ) {
		return updateDate;
	}

	public void setUpdateDate( Date updateDate ) {
		this.updateDate = updateDate;
	}

	public Collection< Comment > getComments( ) {
		return comments;
	}

	public void setComments( ArrayList< Comment > comments ) {
		this.comments = comments;
	}
}
