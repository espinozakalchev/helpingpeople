package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * Date: 6/8/13
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class CanPost extends Post {
    private Blob picture;
    private String description;
    private User user;
    private Date createdDate;
    private Date modifiedDate;
    
    private boolean closed = false;

	public CanPost(Blob picture, String description, User user,
			Date createdDate, Date modifiedDate) {
		super();
		this.picture = picture;
		this.description = description;
		this.user = user;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		
		create();
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}
}
