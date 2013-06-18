package models;


import java.util.Date;

import javax.persistence.Entity;

@Entity
public class NeedPost extends Post {
	public NeedPost(String title, String description, User user, Date createdDate) {
		super(title, description, user, createdDate);
		
		create();
	}
}
