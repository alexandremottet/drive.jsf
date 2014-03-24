package fr.isima.drivejsf.ejb;

import fr.isima.drivejsf.dao.DocumentDAO;
import fr.isima.drivejsf.dao.ShareDAO;
import fr.isima.drivejsf.entity.Document;
import fr.isima.drivejsf.entity.Share;
import fr.isima.drivejsf.entity.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class ShareServiceEJB {

    @EJB
    private UserServiceEJB userService;

    @EJB
    private DocumentServiceEJB documentService;

    public void shareDocument (String sharedUserLogin, int documentId) {

        User user = userService.getUserWithLogin(sharedUserLogin);
        Document document = documentService.getDocument(documentId);
        Share shareElement = new Share();

        shareElement.setUserid(user);
        shareElement.setDocid(document);

        ShareDAO dao = new ShareDAO();
        dao.share(shareElement);

    }

}
