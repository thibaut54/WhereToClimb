package org.thibaut.wheretoclimb.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Table(name="element")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Element {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	@Size(min=4,max=50)
	private String name;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	@OneToMany/*(mappedBy = "element", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "element_id")
	private Collection< Comment > comments;


//----------CONSTRUCTORS----------

	public Element( ) {
	}

	public Element( String name, LocalDateTime createDate, LocalDateTime updateDate,
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

	public LocalDateTime getCreateDate( ) {
		return createDate;
	}

	public void setCreateDate( LocalDateTime createDate ) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate( ) {
		return updateDate;
	}

	public void setUpdateDate( LocalDateTime updateDate ) {
		this.updateDate = updateDate;
	}

	public Collection< Comment > getComments( ) {
		return comments;
	}

	public void setComments( ArrayList< Comment > comments ) {
		this.comments = comments;
	}
}
