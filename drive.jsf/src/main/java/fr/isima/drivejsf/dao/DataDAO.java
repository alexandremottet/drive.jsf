package fr.isima.drivejsf.dao;

import fr.isima.drivejsf.entity.Data;
import fr.isima.drivejsf.exception.NoDataFoundException;
import fr.isima.drivejsf.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataDAO {

    public Data getDataForMD5Hash (String md5Hash) {
        Session session = HibernateUtil.getSession();

        Query query = session.getNamedQuery("Data.findByHash");
        query.setString("hash", md5Hash);

        Data data = (Data) query.uniqueResult();

        if (data == null) {
            throw new NoDataFoundException();
        }

        session.close();

        return data;
    }

    public Data saveData(Data data) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        data = (Data) session.merge(data);
        session.saveOrUpdate(data);

        transaction.commit();
        session.close();

        return data;
    }
}
