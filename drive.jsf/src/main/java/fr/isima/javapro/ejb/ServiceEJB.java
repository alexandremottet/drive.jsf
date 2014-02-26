package fr.isima.javapro.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isima.javapro.domain.User;

/**
 * Session Bean implementation class ServiceEJB
 */
@Singleton
@LocalBean
public class ServiceEJB {
	
	@PersistenceContext(name="PrimeFacesExperimentDataSource")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public ServiceEJB() {
    	
    }
    
    public User signIn(String login, String password) {
    	User user = new User(login, password);
    
    	//em.persist(user);
    	
    	return user;
    }

}
