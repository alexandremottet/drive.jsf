package fr.isima.drivejsf.ejb;

import fr.isima.drivejsf.entity.User;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

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
    	User user = new User();

    	//em.persist(user);
    	
    	return user;
    }

}
