package controllers;

import models.Post;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.util.Date;

import static controllers.Security.isConnected;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * To change this template use File | Settings | File Templates.
 */

public class Users extends Controller {

    public static void signup(){
        /*
         * If the user is already signed in, redirect to Index page
         */
        if(isConnected()){
            Application.index();
        } else {
            render();
        }
    }

    public static void create(String username, String email, String password, String confirmPassword, String firstName, String lastName, String phone, String occupation, String description){
        boolean valid = true;
        String errors="";
        if(User.find("byUsername",username).first() !=  null){
            errors="Username already exists<br/>";
            valid=false;
        }
        if(User.find("byEmail",email).first() != null){
            errors+="Email already exists<br/>";
            valid=false;
        }
        if(!password.equals(confirmPassword)){
            errors+="Password and Confirm password do not match<br/>";
            valid=false;
        }

        if(valid){
            new User(username, email, password, confirmPassword, firstName, lastName, null, null, null);
            session.put("username", username); //Sign in the user, automatically, after successful registration
        } else {
            flash.error(errors);
            signup(); // redirect to signup page again on error
        }

        Application.index();
    }

    public static void viewUserProfile(Long userId) {
    	if(!session.contains("username")) {
            try {
                Secure.login();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
		User user = User.findById(userId);
		User loggedInUser = User.find("byUsername",Security.connected()).first();
		render(user, loggedInUser);
	}
}
