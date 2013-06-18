package models;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class CanPost extends Post {
	public CanPost(String title, String description, User user, Date createdDate) {
		super(title, description, user, createdDate);
		
		create();
	}
}
