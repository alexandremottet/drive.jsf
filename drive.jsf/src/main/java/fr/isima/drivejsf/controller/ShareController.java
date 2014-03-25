package fr.isima.drivejsf.controller;

import fr.isima.drivejsf.ejb.DocumentServiceEJB;
import fr.isima.drivejsf.ejb.ShareServiceEJB;
import fr.isima.drivejsf.ejb.UserServiceEJB;
import fr.isima.drivejsf.entity.Data;
import fr.isima.drivejsf.entity.Document;
import fr.isima.drivejsf.entity.Share;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@ManagedBean
@SessionScoped
public class ShareController implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

    /**
     * Documents shared To Me
     */
    private List<Document> documentsTM;
    /**
     * Documents shared From Me
     */
    private List<Share> documentsFM;

    /**
     * Selected document To Me
     */
    private Document selectedDocumentTM = null;
    /**
     * Selected document From Me
     */
    private Share selectedDocumentFM = null;

    /**
     * Downloadable document To Me
     */
    private StreamedContent downloadableDocumentTM = null;
    /**
     * Downloadable document From Me
     */
    private StreamedContent downloadableDocumentFM = null;

    private String currentUserId = "1";

    private Document currentDocumentTM = null;
    private Share currentDocumentFM = null;

    private String currentPathTM = "";
    private String currentPathFM = "";

    @EJB
    private DocumentServiceEJB documentService;

    @EJB
    private UserServiceEJB userService;

    @EJB
    private ShareServiceEJB shareService;

    @PostConstruct
    private void postConstruct() {
        documentsTM = documentService.getSharedWithMeList(currentUserId);
        documentsFM = documentService.getSharedList(currentUserId);
    }

    private void setFileStreamedContentTM(Document document) {
        Data data;
        InputStream stream;

        data = document.getDataid();
        stream = new ByteArrayInputStream(data.getData());
        downloadableDocumentTM = new DefaultStreamedContent(stream, "text/plain", document.getName());
    }

    private void setFileStreamedContentFM(Document document) {
        Data data;
        InputStream stream;

        data = document.getDataid();
        stream = new ByteArrayInputStream(data.getData());
        downloadableDocumentFM = new DefaultStreamedContent(stream, "text/plain", document.getName());
    }

    private void zipFolder (Document folder, ZipOutputStream zos, String root) throws IOException {
        List<Document> children = documentService.getList(currentUserId, folder.getId().toString());

        for (Document child : children) {
            if (documentService.isFolder(child.getId().toString())) {
                zipFolder(child, zos, root + "/" + child.getName());
            } else {
                ZipEntry entry = new ZipEntry(root + "/" + child.getName());
                Data data = child.getDataid();

                entry.setSize(data.getData().length);
                zos.putNextEntry(entry);
                zos.write(data.getData());
                zos.closeEntry();
            }
        }
    }

    private void setFolderStreamContentTM(Document folder) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        InputStream stream;

        try {
            zipFolder(folder, zos, "/" + folder.getName());
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stream = new ByteArrayInputStream(baos.toByteArray());

        downloadableDocumentTM = new DefaultStreamedContent(stream, "text/plain", folder.getName() + ".zip");

    }

    private void setFolderStreamContentFM(Document folder) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        InputStream stream;

        try {
            zipFolder(folder, zos, "/" + folder.getName());
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stream = new ByteArrayInputStream(baos.toByteArray());

        downloadableDocumentFM = new DefaultStreamedContent(stream, "text/plain", folder.getName() + ".zip");

    }

    private void updateTM() {
        Document current = currentDocumentTM;

        if (current != null) {
            documentsTM = documentService.getList(currentUserId, current.getId().toString());
            currentPathTM = current.getUri();
        } else {
            documentsTM = documentService.getSharedWithMeList(currentUserId);
            currentPathTM = "";
        }
    }

    private void updateFM() {
        Document current = currentDocumentFM.getDocid();

        /*if (current != null) {
            documentsFM = documentService.getList(currentUserId, current.getId().toString());
            currentPathFM = current.getUri();
        } else {*/
            documentsFM = documentService.getSharedList(currentUserId);
            currentPathFM = "";
        //}
    }

    public List<Document> getDocumentsTM() {
        return documentsTM;
    }

    public void setDocumentsTM(List<Document> documentsTM) {
        this.documentsTM = documentsTM;
    }

    public List<Share> getDocumentsFM() {
        return documentsFM;
    }

    public void setDocumentsFM(List<Share> documentsFM) {
        this.documentsFM = documentsFM;
    }

    public Document getSelectedDocumentTM() {
        return selectedDocumentTM;
    }

    public void setSelectedDocumentTM(Document selectedDocumentTM) {
        this.selectedDocumentTM = selectedDocumentTM;
    }

    public Share getSelectedDocumentFM() {
        return selectedDocumentFM;
    }

    public void setSelectedDocumentFM(Share selectedDocumentFM) {
        this.selectedDocumentFM = selectedDocumentFM;
    }

    public String getCurrentPathTM() {
        return currentPathTM;
    }

    public void setCurrentPathTM(String currentPathTM) {
        this.currentPathTM = currentPathTM;
    }

    public String getCurrentPathFM() {
        return currentPathFM;
    }

    public void setCurrentPathFM(String currentPathFM) {
        this.currentPathFM = currentPathFM;
    }

    public StreamedContent getDownloadableDocumentTM() {
        Document current = selectedDocumentTM;

        if (current != null) {
            if (documentService.isFolder(current.getId().toString())) {
                setFolderStreamContentTM(current);
            } else {
                setFileStreamedContentTM(current);
            }

        } else {
            downloadableDocumentTM = null;
        }

        return downloadableDocumentTM;
    }

    public StreamedContent getDownloadableDocumentFM() {
        Document current = selectedDocumentFM.getDocid();

        if (current != null) {
            if (documentService.isFolder(current.getId().toString())) {
                setFolderStreamContentFM(current);
            } else {
                setFileStreamedContentFM(current);
            }

        } else {
            downloadableDocumentFM = null;
        }

        return downloadableDocumentFM;
    }

    public void setDownloadableDocumentTM(StreamedContent downloadableDocumentTM) {
        this.downloadableDocumentTM = downloadableDocumentTM;
    }

    public void setDownloadableDocumentFM(StreamedContent downloadableDocumentFM) {
        this.downloadableDocumentFM = downloadableDocumentFM;
    }

    public void onDocumentDblClckTM() {
        if (selectedDocumentTM != null) {
            currentDocumentTM = selectedDocumentTM;

            updateTM();
        }
    }

    public void onDocumentDblClckFM() {
        if (selectedDocumentFM != null) {
            currentDocumentFM = selectedDocumentFM;

            updateFM();
        }
    }

    public void onDocumentSelectTM() {

    }

    public void onDocumentSelectFM() {

    }

    public void onReturnToParentTM() {
        if (documentsTM != null && currentDocumentTM != null) {
            currentDocumentTM = currentDocumentTM.getParentid();

            updateTM();
        }
    }

    public void undoShareFM () {
        if (selectedDocumentFM != null) {
            shareService.unshare(selectedDocumentFM);
        }

    }
}