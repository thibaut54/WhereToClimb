package org.thibaut.wheretoclimb.webapp.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public abstract class ElementForm {

	private Integer id;

	private String name;

	private List< Comment > comments;

	private LocalDateTime createDate;
	private LocalDateTime updateDate;

}
