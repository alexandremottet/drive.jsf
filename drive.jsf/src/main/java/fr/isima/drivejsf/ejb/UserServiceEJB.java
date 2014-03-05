package fr.isima.drivejsf.ejb;

import fr.isima.drivejsf.dao.UserDAO;
import fr.isima.drivejsf.entity.User;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
@LocalBean
public class UserServiceEJB {

    public UserServiceEJB() {
    	
    }

    public User getUser (String userId) {
        return new UserDAO().getUser(Integer.parseInt(userId));
    }

    public List<String> getLoginList() {
        return new UserDAO().getList();
    }

}
