package fr.isima.drivejsf.ejb;

import fr.isima.drivejsf.entity.User;
import fr.isima.drivejsf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import fr.isima.drivejsf.entity.User;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Session Bean implementation class ServiceEJB
 */
@Singleton
@LocalBean
public class ServiceEJB {
	
	//@PersistenceContext(name="PrimeFacesExperimentDataSource")
	//EntityManager em;

    /**
     * Default constructor. 
     */
    public ServiceEJB() {
    	
    }
    
    public User signIn(String login, String password) {

        Session s = HibernateUtil.getSession();
        s.beginTransaction();
        Query q = s.getNamedQuery("User.findAll");

    	List<User> users = q.list();

        s.getTransaction().commit();
    	
    	return users.get(0);


    }

}
