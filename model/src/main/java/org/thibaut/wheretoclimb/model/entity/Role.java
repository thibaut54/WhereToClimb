package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

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
@ToString
@EqualsAndHashCode
public class Role {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String role;
	@ManyToMany(mappedBy = "roles")
	private List< User > users;


//----------CONSTRUCTORS----------


	public Role( String role ) {
		this.role = role;
	}


//----------GETTERS & SETTERS----------

}
