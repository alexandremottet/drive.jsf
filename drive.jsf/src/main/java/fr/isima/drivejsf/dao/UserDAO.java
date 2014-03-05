package fr.isima.drivejsf.dao;

import fr.isima.drivejsf.entity.User;
import fr.isima.drivejsf.exception.NoDataFoundException;
import fr.isima.drivejsf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserDAO {

    public User getUser (int userId) {
        Session session = HibernateUtil.getSession();

        Query query = session.getNamedQuery("User.findById");
        query.setInteger("id", userId);

        User user = (User) query.uniqueResult();

        if (user == null) {
            throw new NoDataFoundException();
        }

        session.close();

        return user;
    }

    public List<String> getList() {
        Session session = HibernateUtil.getSession();

        Query query = session.getNamedQuery("User.findAllLogin");
        List<String> users = query.list();

        if (users == null)
            throw new NoDataFoundException();

        return users;

    }

}
