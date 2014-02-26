package fr.isima.drivejsf.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "blob", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"hash"})})
@NamedQueries({
    @NamedQuery(name = "Blob.findAll", query = "SELECT b FROM Blob b"),
    @NamedQuery(name = "Blob.findById", query = "SELECT b FROM Blob b WHERE b.id = :id"),
    @NamedQuery(name = "Blob.findByHash", query = "SELECT b FROM Blob b WHERE b.hash = :hash"),
    @NamedQuery(name = "Blob.findByCounter", query = "SELECT b FROM Blob b WHERE b.counter = :counter")})
public class Blob implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 200)
    @Column(name = "hash", length = 200)
    private String hash;
    @Lob
    @Column(name = "data")
    private byte[] data;
    @Column(name = "counter")
    private Integer counter;
    @OneToMany(mappedBy = "blobid")
    private List<Document> documentList;

    public Blob() {
    }

    public Blob(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blob)) {
            return false;
        }
        Blob other = (Blob) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.isima.drivejsf.entity.Blob[ id=" + id + " ]";
    }
    
}
