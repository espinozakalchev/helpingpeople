package models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.util.Date;
import play.db.jpa.Model;

@Entity
public class Reference extends Model{
	
	@ManyToOne(targetEntity=User.class)
	private User fromUser; // user who is writing a reference
	@ManyToOne(targetEntity=User.class)
	private User toUser; // user the reference is written for
	@NotEmpty
	private String referenceText;		
	@NotNull
	private Date date;
	
	public Reference(User fromUser, User toUser, String referenceText) {
		super();
		this.fromUser = fromUser;
		this.referenceText = referenceText;		
		this.toUser = toUser;
		this.date = new Date();
		
		create();	
	}

	

	@NotEmpty
	@Lob
	public String getReferenceText() {
		return referenceText;
	}

	public void setReferenceText(String referenceText) {
		this.referenceText = referenceText;
	}
		

	public User getFromUser() {
		return fromUser;
	}


	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}



	public User getToUser() {
		return toUser;
	}


	public void setToUser(User toUser) {
		this.toUser = toUser;
	}



	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
