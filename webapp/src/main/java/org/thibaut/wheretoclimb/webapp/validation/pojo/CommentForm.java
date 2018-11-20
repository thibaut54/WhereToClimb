package org.thibaut.wheretoclimb.webapp.validation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Element;
import org.thibaut.wheretoclimb.model.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CommentForm {

//----------ATTRIBUTES----------

	private Integer id;
	private User user;
	private Element element;
	private Integer elementId;
	private String title;
	private String content;
	private LocalDateTime createDate;

	public CommentForm(Integer elementId){
		this.elementId = elementId;
	}

}
