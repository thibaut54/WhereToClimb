package org.thibaut.wheretoclimb.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments") // **** CHECK IF THIS NAME WORKS WITH SQL!!!
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

	public Comment( ) {
	}

	public Comment( String title, String content, Date createDate, Date updateDate ) {
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}


//----------GETTERS & SETTERS----------

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getTitle( ) {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public String getContent( ) {
		return content;
	}

	public void setContent( String content ) {
		this.content = content;
	}

	public Date getCreateDate( ) {
		return createDate;
	}

	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
	}

	public Date getUpdateDate( ) {
		return updateDate;
	}

	public void setUpdateDate( Date updateDate ) {
		this.updateDate = updateDate;
	}
}
