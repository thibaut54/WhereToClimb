package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Bean used to define the common attributs of beans used for communication
 */
@Entity
@Table(name="communication")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class Communication {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String content;
	private LocalDateTime createDate;
	@ManyToOne
	@JoinColumn(name = "user_emitter_id")
	private User userEmitter;
}
