package fr.isima.drivejsf.dao;

import fr.isima.drivejsf.entity.Document;
import fr.isima.drivejsf.entity.Share;
import fr.isima.drivejsf.exception.NoDataFoundException;
import fr.isima.drivejsf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by alexandre on 24/03/14.
 */
public class ShareDAO {

    public Boolean exist (int docid, int userid) {

        Boolean exist = true;
        Session session = HibernateUtil.getSession();

        Query query = session.getNamedQuery("Share.findByDocIdAndUserId");
        query.setInteger("docid", docid);
        query.setInteger("userid", userid);

        Share shareElement = (Share) query.uniqueResult();

        if (shareElement == null) {
            exist = false;
        }

        return exist;

    }

    public Boolean exist (Share shareElement) {
        return exist(shareElement.getDocid().getId(), shareElement.getUserid().getId());
    }

    public void share(Share shareElement) {

        if (!exist(shareElement)) {

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();

            shareElement = (Share) session.merge(shareElement);
            session.saveOrUpdate(shareElement);

            transaction.commit();
            session.close();

        }

    }

}
