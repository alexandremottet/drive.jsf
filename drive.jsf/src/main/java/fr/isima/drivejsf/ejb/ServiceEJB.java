package fr.isima.drivejsf.ejb;

import fr.isima.drivejsf.dao.DocumentDAO;
import fr.isima.drivejsf.entity.Data;
import fr.isima.drivejsf.entity.Document;
import fr.isima.drivejsf.entity.User;
import fr.isima.drivejsf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import java.util.List;

/**
 * Session Bean implementation class ServiceEJB
 */
@Singleton
@LocalBean
public class ServiceEJB {

    /**
     * Default constructor. 
     */
    public ServiceEJB() {
    	
    }

    public boolean isRoot (String documentId) {
        return new DocumentDAO().isRoot(Integer.parseInt(documentId));
    }

    public boolean isFolder (String documentId) {
        return new DocumentDAO().isFolder(Integer.parseInt(documentId));
    }

    public void deleteDocument (String documentId) {
        new DocumentDAO().deleteDocument(Integer.parseInt(documentId));
    }

    public List<Document> getList (String ownerId, String documentId) {

        if (documentId == null)
            return new DocumentDAO().getDocumentRoot(Integer.parseInt(ownerId));

        return new DocumentDAO().getFolderList(Integer.parseInt(documentId));

    }

    public Document getDocument (String documentId) {
        return new DocumentDAO().getDocument(Integer.parseInt(documentId));
    }

    public Document getDocumentForUri (String ownerId, String documentUri) {
        return new DocumentDAO().getDocumentForUri(Integer.parseInt(ownerId), documentUri);
    }

}
