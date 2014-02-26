package fr.isima.drivejsf.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.isima.drivejsf.ejb.ServiceEJB;
import fr.isima.drivejsf.entity.User;

@ManagedBean
@SessionScoped
public class MainController implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

	@EJB
	private ServiceEJB service;
	
	private String login;
	private String password;
	private User user;
	
	public void doLogin() {
		System.out.println("DoLogin" + login  + " " + password);
		this.user = service.signIn(login, password);
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}