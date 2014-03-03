/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.isima.drivejsf.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Drusy
 */
@Entity
@Table(name = "document")
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findById", query = "SELECT d FROM Document d WHERE d.id = :id"),
    @NamedQuery(name = "Document.findByName", query = "SELECT d FROM Document d WHERE d.name = :name"),
    @NamedQuery(name = "Document.findRootByOwner", query = "SELECT d FROM Document d WHERE d.ownerid = :ownerId AND d.parentid = NULL"),
    @NamedQuery(name = "Document.findFolderContent", query = "SELECT d FROM Document d WHERE d.parentid = :parentId"),
    @NamedQuery(name = "Document.findByUri", query = "SELECT d FROM Document d WHERE d.ownerid = :ownerId AND d.uri = :uri"),
    @NamedQuery(name = "Document.countFolderContent", query = "SELECT COUNT(d) FROM Document d WHERE d.parentid = :parentId")
})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;

    @Lob
    @Size(max = 65535)
    @Column(name = "uri", length = 65535)
    private String uri;

    @OneToMany(mappedBy = "parentid", fetch = FetchType.EAGER)
    private List<Document> documentList;

    @JoinColumn(name = "parentid", referencedColumnName = "id")
    @ManyToOne
    private Document parentid;

    @JoinColumn(name = "ownerid", referencedColumnName = "id")
    @ManyToOne
    private User ownerid;

    @JoinColumn(name = "dataid", referencedColumnName = "id")
    @ManyToOne
    private Data dataid;

    @OneToMany(mappedBy = "docid")
    private List<Share> shareList;

    public Document() {
    }

    public Document(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public Document getParentid() {
        return parentid;
    }

    public void setParentid(Document parentid) {
        this.parentid = parentid;
    }

    public User getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(User ownerid) {
        this.ownerid = ownerid;
    }

    public Data getDataid() {
        return dataid;
    }

    public void setDataid(Data dataid) {
        this.dataid = dataid;
    }

    public List<Share> getShareList() {
        return shareList;
    }

    public void setShareList(List<Share> shareList) {
        this.shareList = shareList;
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
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.isima.drivejsf.entity.Document[ id=" + id + " ]";
    }
    
}
