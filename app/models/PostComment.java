package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import play.db.jpa.Model;

@Entity
public class PostComment extends Model {
	private User user;
	
	@ManyToOne
	private Post post;
	
	private Date postedOn;
	private String comment;
	
	public PostComment(User user, Post post, String comment) {
		super();
		this.user = user;
		this.post = post;
		this.postedOn = new Date();
		this.comment = comment;
		
		create();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	
	@NotEmpty
	@Lob
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
