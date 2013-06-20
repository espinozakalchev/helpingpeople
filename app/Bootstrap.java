import models.CanPost;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

import java.util.Date;


@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
        User user = new User("administrator","admin@helpingpeople.com","password","password", "Admin","admin",new Date(),"123","Developer","Nothing much");
        if (CanPost.count() == 0)
			Fixtures.loadModels("posts.yml");

	}
}
