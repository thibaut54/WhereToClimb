package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

/**
 * Bean used to store the different roles that users can have
 */
@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Role {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String role;
	@ManyToMany(mappedBy = "roles")
	private Collection< User > users;


//----------CONSTRUCTORS----------


	public Role( String role ) {
		this.role = role;
	}


//----------GETTERS & SETTERS----------

}
