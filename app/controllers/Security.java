package controllers;

import models.User;
import play.libs.Crypto;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * To change this template use File | Settings | File Templates.
 */
public class Security extends Secure.Security {
    static boolean authenticate(String username, String password){
        User user = User.find("byUsername", username).first();
        return ( (user !=null) && ( user.getPassword().equals(new Crypto().encryptAES(password)) ) );
    }

    public static void onDisconnected() {
        Application.index();
    }

    public static User getCurrentUser() {
    	if (session.contains("username")) {
    		return User.find("byUsername", session.get("username")).first();
    	}
    	
    	return null;
    }
}
