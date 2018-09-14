package org.thibaut.wheretoclimb.model.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Bean used to store the different roles that users can have
 */
@Entity
@Table(name = "role")
public class Role {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String role;

	@ManyToMany(mappedBy = "roles")
	private Collection< User > users;



//----------CONSTRUCTORS----------

	public Role( ) {
	}

	public Role( String role ) {
		this.role = role;
	}


//----------GETTERS & SETTERS----------

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getRole( ) {
		return role;
	}

	public void setRole( String role ) {
		this.role = role;
	}

	public Collection< User > getUsers( ) {
		return users;
	}

	public void setUsers( Collection< User > users ) {
		this.users = users;
	}
}
