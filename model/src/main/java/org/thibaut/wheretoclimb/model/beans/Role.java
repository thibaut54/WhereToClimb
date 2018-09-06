package org.thibaut.wheretoclimb.model.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Bean used to store the different roles that users can have
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String role;
//	@OneToOne
//	@JoinColumn(name = "role_id")
//	private User user;

//	@ManyToMany(mappedBy = "roles")
////	@ManyToOne
////	@JoinTable(
////			name = "roles_of_users",
////			joinColumns = { @JoinColumn(name = "role_role", referencedColumnName = "role_name") },
////			inverseJoinColumns = { @JoinColumn(name = "user_email", referencedColumnName = "email") })
//	private Collection< User > users;



//----------CONSTRUCTORS----------

	public Role( ) {
	}

	public Role( String role ) {
		this.role = role;
	}

//	public Role( String role, Collection< User > users ) {
//		this.role = role;
//		this.users = users;
//	}


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

//	public Collection< User > getUsers( ) {
//		return users;
//	}
//
//	public void setUsers( Collection< User > users ) {
//		this.users = users;
//	}
}
