package fr.isima.drivejsf.dao;

import fr.isima.drivejsf.entity.Document;
import fr.isima.drivejsf.entity.Share;
import fr.isima.drivejsf.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by alexandre on 24/03/14.
 */
public class ShareDAO {

    public void share(Share shareElement) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        shareElement = (Share) session.merge(shareElement);
        session.saveOrUpdate(shareElement);

        transaction.commit();
        session.close();
    }

}
