package fr.isima.drivejsf.ejb;

import fr.isima.drivejsf.dao.DataDAO;
import fr.isima.drivejsf.entity.Data;
import fr.isima.drivejsf.exception.NoDataFoundException;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Singleton
@LocalBean
public class DataServiceEJB {

    public DataServiceEJB() {
    	
    }

    public Data getDataForByteArray(byte[] byteArray) {
        Data data = null;
        String md5Hash = null;
        MessageDigest md5Digest;
        DataDAO dao = new DataDAO();

        try {
            md5Digest = MessageDigest.getInstance("MD5");
            md5Hash = (new HexBinaryAdapter()).marshal(md5Digest.digest(byteArray));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            data = dao.getDataForMD5Hash(md5Hash);
        } catch (NoDataFoundException e) {
            data = new Data();

            data.setHash(md5Hash);
            data.setData(byteArray);

            data = dao.saveData(data);
        }

        return data;
    }
}
