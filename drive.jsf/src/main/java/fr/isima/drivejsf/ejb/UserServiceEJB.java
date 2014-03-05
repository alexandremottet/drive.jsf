package fr.isima.drivejsf.ejb;

import fr.isima.drivejsf.dao.UserDAO;
import fr.isima.drivejsf.entity.User;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class UserServiceEJB {

    public UserServiceEJB() {
    	
    }

    public User getUser (String userId) {
        return new UserDAO().getUser(Integer.parseInt(userId));
    }


}
