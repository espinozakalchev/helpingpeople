import models.CanPost;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

import java.util.Date;
import java.util.List;


@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
        User user = null;
        if(User.find("byUsername","administrator").first() == null){
            user = new User("administrator","admin@helpingpeople.com","password","password", "Admin","admin","123","Developer","Nothing much");
        }
        if (CanPost.count() == 0){
            try {
                Fixtures.deleteAllModels();
			    Fixtures.loadModels("posts.yml");
                List<CanPost> canposts = CanPost.findAll();
                for(CanPost canpost : canposts){
                    canpost.setUser(user);
                    canpost.save();
                }
            } catch (Exception e){
                //e.printStackTrace();
            }

        }

	}
}
