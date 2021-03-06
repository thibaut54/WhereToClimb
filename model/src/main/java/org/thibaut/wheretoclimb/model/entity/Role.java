package org.thibaut.wheretoclimb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Bean used to store the different roles that users can have
 */
@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Role {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToMany(mappedBy = "roles")
	private List< User > users;

	private String role;



//----------CONSTRUCTORS----------


	public Role( String role ) {
		this.role = role;
	}


//----------GETTERS & SETTERS----------

}
