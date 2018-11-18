package org.thibaut.wheretoclimb.webapp.validation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestForm {

	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class )
	@JsonSerialize(using = LocalDateSerializer.class )
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class )
	@JsonSerialize(using = LocalDateSerializer.class )
	private LocalDate endDate;

	private Atlas atlas;

	private User user;

	private LocalDateTime createDate;
	private String message;
	private boolean accepted;

}
