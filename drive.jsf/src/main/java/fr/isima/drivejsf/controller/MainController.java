package fr.isima.drivejsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.isima.drivejsf.dao.DocumentDAO;
import fr.isima.drivejsf.ejb.ServiceEJB;
import fr.isima.drivejsf.entity.Document;
import fr.isima.drivejsf.entity.User;

@ManagedBean
@SessionScoped
public class MainController implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

	@EJB
	private ServiceEJB service;
	
	public void doLogin() {

        //System.out.println("isRoot : " + service.isRoot ("1"));
        //System.out.println("isFolder : " + service.isFolder ("1"));
        //System.out.println("getList : " + service.getList ("1", null));
        //System.out.println("getList : " + service.getList ("1", "1"));
        //System.out.println("getDocument : " + service.getDocument ("1"));
        //System.out.println("getDocumentForUri : " + service.getDocumentForUri ("2", "aleanar_folder"));

    }
	
}