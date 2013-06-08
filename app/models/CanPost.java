package models;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * Date: 6/8/13
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class CanPost {
    private String description;
    private User user;
    private Date createdDate;
    private Date modifiedDate;
    private boolean isDeleted;

}
