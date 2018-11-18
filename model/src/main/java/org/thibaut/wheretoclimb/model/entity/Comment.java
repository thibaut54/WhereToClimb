package org.thibaut.wheretoclimb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Bean used to define the common attributs of beans used for communication
 */
@Entity
@Table(name="comments")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Comment {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "element_id")
	private Element element;

	private String title;
	private String content;
	private LocalDateTime createDate;

}
