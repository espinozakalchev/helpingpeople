package models;

import java.io.File;
import java.util.Date;
import java.util.List;
import javax.persistence.Transient;

import play.db.jpa.Model;

public abstract class Profile extends Model {
	
	@Transient
	public String getPhotoPath(long id) {
		String path = String.format("public/user/%d", id);
		
		File dir = new File(path);
		if (dir != null && dir.isDirectory()) {
			 File[] images = dir.listFiles();
			 if (images.length > 0) {
				 return "/".concat(path).concat("/").concat(images[0].getName());
			 }
		}
		
		
		return "/public/images/logo.png";
	}
	
	@Transient
	public boolean hasImage(long id) {
		String path = String.format(
				"public/posts/%d", id);
		
		File dir = new File(path);
		if (dir != null && dir.isDirectory()) {
			 File[] images = dir.listFiles();
			 return images.length > 0;
		}
		
		return false;
	}
	
}
