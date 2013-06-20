package controllers;

import models.User;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.mvc.Controller;
import play.mvc.With;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * Date: 6/19/13
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */

public class Users extends Controller {

    public static void signup(){
        render();
    }
    public static void create(@Required @Unique String username,@Required @Email String email,@Required String password,@Required @Equals("password") String confirmPassword, String firstName, String lastName, Date dateOfBirth, String phone, String occupation, String description){
        new User(username, email, password, confirmPassword, firstName, lastName, dateOfBirth, null, null, null);
        Application.index();
    }
}