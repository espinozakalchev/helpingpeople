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
	}
}
