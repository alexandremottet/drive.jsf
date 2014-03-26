package fr.isima.drivejsf.dao;

import fr.isima.drivejsf.entity.User;
import fr.isima.drivejsf.exception.NoDataFoundException;
import fr.isima.drivejsf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public User getUser (String login) {
        Session session = HibernateUtil.getSession();

        Query query = session.getNamedQuery("User.findByLogin");
        query.setString("login", login);

        User user = (User) query.uniqueResult();

        if (user == null) {
            throw new NoDataFoundException();
        }

        session.close();

        return user;
    }

    public User getUserByEmail (String email) {
        Session session = HibernateUtil.getSession();

        Query query = session.getNamedQuery("User.findByEmail");
        query.setString("email", email);

        User user = (User) query.uniqueResult();

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

    public User createUser(String email, String login) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        User user = new User();
        user.setEmail(email);
        user.setLogin(login);

        session.saveOrUpdate(user);

        transaction.commit();
        session.close();

        return user;
    }
}
