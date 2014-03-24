/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.isima.drivejsf.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Drusy
 */
@Entity
@Table(name = "share")
@NamedQueries({
    @NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s"),
    @NamedQuery(name = "Share.findByIdshare", query = "SELECT s FROM Share s WHERE s.idshare = :idshare"),
    @NamedQuery(name = "Share.findByDocIdAndUserId", query = "SELECT s FROM Share s WHERE s.docid = :docid AND s.userid = :userid")
})
public class Share implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idshare", nullable = false)
    private Integer idshare;
    @JoinColumn(name = "docid", referencedColumnName = "id")
    @ManyToOne
    private Document docid;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne
    private User userid;

    public Share() {
    }

    public Share(Integer idshare) {
        this.idshare = idshare;
    }

    public Integer getIdshare() {
        return idshare;
    }

    public void setIdshare(Integer idshare) {
        this.idshare = idshare;
    }

    public Document getDocid() {
        return docid;
    }

    public void setDocid(Document docid) {
        this.docid = docid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idshare != null ? idshare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Share)) {
            return false;
        }
        Share other = (Share) object;
        if ((this.idshare == null && other.idshare != null) || (this.idshare != null && !this.idshare.equals(other.idshare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.isima.drivejsf.entity.Share[ idshare=" + idshare + " ]";
    }
    
}
