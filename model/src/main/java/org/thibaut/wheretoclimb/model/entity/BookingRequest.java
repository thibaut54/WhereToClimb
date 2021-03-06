package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="booking_request")
@Getter
@Setter
@NoArgsConstructor
public class BookingRequest {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate startDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name = "atlas_id")
	private Atlas atlas;

	@ManyToOne
	@JoinColumn(name = "user_emitter_id")
	private User user;

	private LocalDateTime createDate;
	private String message;
	private boolean accepted;

}
