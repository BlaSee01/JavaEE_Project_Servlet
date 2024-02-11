/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MP3.war;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author student
 */
@Entity
@Table(name = "SONGS")
@NamedQueries({
    @NamedQuery(name = "Songs.findAll", query = "SELECT s FROM Songs s"),
    @NamedQuery(name = "Songs.findById", query = "SELECT s FROM Songs s WHERE s.id = :id"),
    @NamedQuery(name = "Songs.findByTitle", query = "SELECT s FROM Songs s WHERE s.title = :title"),
    @NamedQuery(name = "Songs.findByArtist", query = "SELECT s FROM Songs s WHERE s.artist = :artist"),
    @NamedQuery(name = "Songs.findByFile", query = "SELECT s FROM Songs s WHERE s.file = :file")})
public class Songs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "ARTIST")
    private String artist;
    @Column(name = "FILE")
    private String file;

    public Songs() {
    }

    public Songs(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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
        if (!(object instanceof Songs)) {
            return false;
        }
        Songs other = (Songs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3.war.Songs[ id=" + id + " ]";
    }
    
}
