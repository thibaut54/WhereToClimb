package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments") // **** CHECK IF THIS NAME WORKS WITH SQL!!!
public class Comment extends Element{

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public Integer getId( ) {
		return id;
	}

	@Override
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

	@Override
	public Date getCreateDate( ) {
		return createDate;
	}

	@Override
	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
	}

	@Override
	public Date getUpdateDate( ) {
		return updateDate;
	}

	@Override
	public void setUpdateDate( Date updateDate ) {
		this.updateDate = updateDate;
	}
}
