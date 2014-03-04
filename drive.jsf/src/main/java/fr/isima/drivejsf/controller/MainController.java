package fr.isima.drivejsf.controller;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import fr.isima.drivejsf.ejb.ServiceEJB;
import fr.isima.drivejsf.entity.Data;
import fr.isima.drivejsf.entity.Document;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class MainController implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

    private List<Document> rootDocuments;
    private Document selectedDocument = null;
    private StreamedContent downloadableDocument = null;
    private String currentUser = "1";
    private Document currentDocument = null;

	@EJB
	private ServiceEJB service;

	public void testDAO() {
        //System.out.println("isRoot : " + service.isRoot ("1"));
        //System.out.println("isFolder : " + service.isFolder ("1"));
        //System.out.println("getList : " + service.getList ("1", null));
        //System.out.println("getList : " + service.getList ("1", "1"));
        //System.out.println("getDocument : " + service.getDocument ("1"));
        //System.out.println("getDocumentForUri : " + service.getDocumentForUri ("2", "aleanar_folder"));
    }

    @PostConstruct
    private void postConstruct() {
        rootDocuments = service.getList(currentUser, null);
    }

    private void setFileStreamedContent(Document document) {
        Data data;
        InputStream stream;

        data = document.getDataid();
        stream = new ByteArrayInputStream(data.getData());
        downloadableDocument = new DefaultStreamedContent(stream, "text/plain", document.getName());
    }

    private void zipFolder (Document folder, ZipOutputStream zos) throws IOException {
        List<Document> children = service.getList(currentUser, folder.getId().toString());

        for (Document child : children) {
            if (service.isFolder(child.getId().toString())) {
                zipFolder(child, zos);
            } else {
                ZipEntry entry = new ZipEntry(child.getName());
                Data data = child.getDataid();

                entry.setSize(data.getData().length);
                zos.putNextEntry(entry);
                zos.write(data.getData());
                zos.closeEntry();
            }
        }
    }

    private void setFolderStreamContent(Document folder) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        InputStream stream;

        try {
            zipFolder(folder, zos);
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stream = new ByteArrayInputStream(baos.toByteArray());

        downloadableDocument = new DefaultStreamedContent(stream, "text/plain", folder.getName() + ".zip");

    }

    public List<Document> getRootDocuments() {
        return rootDocuments;
    }

    public void setRootDocuments(List<Document> rootDocuments) {
        this.rootDocuments = rootDocuments;
    }

    public Document getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(Document selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public StreamedContent getDownloadableDocument() {
        Document current = selectedDocument;

        if (current != null) {
            if (service.isFolder(current.getId().toString())) {
                setFolderStreamContent(current);
            } else {
                setFileStreamedContent(current);
            }

        } else {
            downloadableDocument = null;
        }

        return downloadableDocument;
    }

    public void setDownloadableDocument (StreamedContent downloadableDocument) {
        this.downloadableDocument = downloadableDocument;
    }

    public void onDocumentDblClck() {
        Document current = selectedDocument;
        List<Document> tmp;

        if (current != null) {
            tmp = service.getList (currentUser, current.getId().toString());

            if (tmp != null) {
                rootDocuments = tmp;
                currentDocument = current;
            }
        }
    }

    public void onReturnToParent () {
        List<Document> tmp;
        Document parent;
        String parentId = null;

        if (rootDocuments != null && currentDocument != null) {
            parent = currentDocument.getParentid();

            if (parent != null) {
                parentId = parent.getId().toString();
            }

            tmp = service.getList (currentUser, parentId);

            if (tmp != null) {
                rootDocuments = tmp;
                currentDocument = parent;
            }
        }
    }

    public void onDeleteDocument () {
        Document current = selectedDocument;

        if (current != null) {
            service.deleteDocument (current.getId().toString());

            rootDocuments = service.getList(currentUser, null);
        }
    }
}