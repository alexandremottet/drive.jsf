package fr.isima.drivejsf.ejb;

import fr.isima.drivejsf.dao.UserDAO;
import fr.isima.drivejsf.entity.User;
import fr.isima.drivejsf.exception.NoDataFoundException;

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

    public User getUserWithLogin (String login) throws NoDataFoundException {
        return new UserDAO().getUser(login);
    }

    public User getUserWithEmail (String email) throws NoDataFoundException {
        return new UserDAO().getUserByEmail(email);
    }

    public List<String> getLoginList() {
        return new UserDAO().getList();
    }

    public User createUser(String email, String login) {
        return new UserDAO().createUser(email, login);
    }
}
