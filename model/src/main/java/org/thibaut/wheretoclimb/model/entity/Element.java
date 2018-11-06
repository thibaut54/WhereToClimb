package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="element")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
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
	@OneToMany/*(mappedBy = "element")*/
	@JoinColumn(name = "element_id")
	private List< Comment > comments;


////----------CONSTRUCTORS----------
//
//
//	public Element( String name, LocalDateTime createDate, LocalDateTime updateDate,
//	                ArrayList< Comment > comments ) {
//		this.name = name;
//		this.createDate = createDate;
//		this.updateDate = updateDate;
//		this.comments = comments;
//	}

}
