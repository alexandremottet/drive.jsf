package fr.isima.drivejsf.dao;

import fr.isima.drivejsf.entity.Document;
import fr.isima.drivejsf.exception.NoDataFoundException;
import fr.isima.drivejsf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DocumentDAO {

    public boolean isRoot (int documentId) {

        Document document = getDocument(documentId);

        return isRoot(document);

    }

    public boolean isRoot (Document document) {

        if (document == null) {
            throw new NoDataFoundException();
        }

        return document.getParentid() == null ? true : false;

    }

    public void deleteDocument(int documentId) {
        Document document = getDocument(documentId);
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        document = (Document) session.merge(document);
        session.delete(document);

        transaction.commit();
        session.close();
    }

    public boolean isFolder (int documentId) {

        Document document = getDocument(documentId);

        return isFolder(document);

    }

    public boolean isFolder (Document document) {

        if (document == null) {
            throw new NoDataFoundException();
        }

        return document.getDataid() == null ? true : false;

    }

    public Document getDocument (int documentId) {

        Session session = HibernateUtil.getSession();

        Query query = session.getNamedQuery("Document.findById");
        query.setInteger("id", documentId);

        Document document = (Document) query.uniqueResult();

        if (document == null) {
            throw new NoDataFoundException();
        }

        session.close();

        return document;
    }

    public Document getDocumentForUri (int ownerId, String documentUri) {

        Session session = HibernateUtil.getSession();

        Query query = session.getNamedQuery("Document.findByUri");
        query.setInteger("ownerId", ownerId);
        query.setString("uri", documentUri);

        Document document = (Document) query.uniqueResult();

        if (document == null) {
            throw new NoDataFoundException();
        }

        session.close();

        return document;

    }

    public List<Document> getDocumentRoot (int ownerId) {

        Session session = HibernateUtil.getSession();

        // Folder
        Query query = session.getNamedQuery("Document.findRootByOwner");
        query.setInteger("ownerId", ownerId);
        List<Document> documents = (List<Document>) query.list();

        if (documents == null) {
            throw new NoDataFoundException();
        }

        session.clear();
        session.close();

        // Documents
        return documents;

    }

    public List<Document> getFolderList(int parentId) {

        Document document = getDocument(parentId);

        if (!isFolder(document)) {
            throw new NoDataFoundException();
        }

        return document.getDocumentList();

    }

    public void saveDocument(Document document) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        document = (Document) session.merge(document);
        session.saveOrUpdate(document);

        transaction.commit();
        session.close();
    }
}
