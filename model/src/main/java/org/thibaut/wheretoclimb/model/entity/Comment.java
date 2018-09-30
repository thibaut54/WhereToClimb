package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments") // **** CHECK IF THIS NAME WORKS WITH SQL!!!
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Comment {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	private String content;
	private Date createDate;
	private Date updateDate;


//----------CONSTRUCTORS----------


	public Comment( String title, String content, Date createDate, Date updateDate ) {
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}


}
