package models;

import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import play.libs.Crypto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * Date: 6/8/13
 * Time: 11:44 AM
 */

@Entity
public class User extends Model {
    @Unique
    @Required
    @Column(unique = true)
    private String username;

    @Email
    @Unique
    @Required
    @Column(unique = true)
    private String email;

    @Required
    private String password;
    private String firstName;
    private String lastName;
    //private boolean isAdmin;
    private Date dateOfBirth;
    private String phone;
    private String occupation;
    private String description;

    public User(@Required @Unique String username,@Required @Email String email,@Required String password,@Required @Equals("password") String confirmPassword, String firstName, String lastName, Date dateOfBirth, String phone, String occupation, String description) {
        this.username = username;
        this.email = email;
        this.password = new Crypto().encryptAES(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.occupation = occupation;
        this.description = description;
        create();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new Crypto().encryptAES(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
