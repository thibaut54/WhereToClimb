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

	@OneToMany/*(mappedBy = "element")*/
	@JoinColumn(name = "element_id")
	private List< Comment > comments;

	private LocalDateTime createDate;
	private LocalDateTime updateDate;


}
