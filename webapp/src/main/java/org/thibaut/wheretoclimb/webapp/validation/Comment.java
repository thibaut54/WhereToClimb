package org.thibaut.wheretoclimb.webapp.validation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Element;
import org.thibaut.wheretoclimb.model.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Comment {

//----------ATTRIBUTES----------

	private Integer id;

	private String title;
	private String content;

}
