
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;


import controllers.Profile;
import models.Post;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;


@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
		if(User.find("byUsername","administrator").first() == null) {
			new User("administrator","admin@helpingpeople.com","password","password", "Admin","admin","123","Developer","Nothing much");
		}
		if (Post.count() == 0)
			Fixtures.loadModels("posts.yml");
		
		// Setting userpics for users from posts.yml
		User tom = User.find("byUsername","tom").first();
		User francesca = User.find("byUsername","francesca").first();
		User mark = User.find("byUsername","mark").first();
		
		tom.setPhoto("green.png");
		tom.save();
		francesca.setPhoto("rosa.png");
		francesca.save();
		mark.setPhoto("bee.png");
		mark.save();
		Map<String,Object>  hm = new HashMap<String,Object>(); 
	      // Put elements to the map
	      hm.put(tom.getId().toString(),"public/images/green.png");
	      hm.put(francesca.getId().toString(),"public/images/rosa.png");
	      hm.put(mark.getId().toString(),"public/images/bee.png");
		
	      for (Entry<String, Object> entry : hm.entrySet()) {
	    	    String key = entry.getKey();
	    	    String value = (String) entry.getValue();
	    	 
	        System.out.println("key: " + key + " value: " + hm.get(key));
			
		    File image = new File(value);

		
		File dir = new File(String.format("public/user/%d", Long.parseLong(key)));
		dir.mkdirs();
		File copy = new File(dir, image.getName());

		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(image);
			out = new FileOutputStream(copy);
			IOUtils.copy(in, out);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} 
			catch (IOException e) {}
		}
		
	}
	}
}
