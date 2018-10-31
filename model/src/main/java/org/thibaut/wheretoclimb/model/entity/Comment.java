package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Bean used to define a commentary that a user can attache to any element of the site
 */
@Entity
@Table(name = "comments") // **** CHECK IF THIS NAME WORKS WITH SQL!!!
@PrimaryKeyJoinColumn(name = "com_id")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Comment extends Communication{

//----------ATTRIBUTES----------

	private String title;
	@ManyToOne
	@JoinColumn(name = "element_id")
	private Element element;


//----------CONSTRUCTORS----------


}
