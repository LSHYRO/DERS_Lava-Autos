/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan6
 */
@Entity
@Table(name = "tamanio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tamanio.findAll", query = "SELECT t FROM Tamanio t"),
    @NamedQuery(name = "Tamanio.findByIdTamanio", query = "SELECT t FROM Tamanio t WHERE t.idTamanio = :idTamanio"),
    @NamedQuery(name = "Tamanio.findByDescripcion", query = "SELECT t FROM Tamanio t WHERE t.descripcion = :descripcion")})
public class Tamanio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTamanio")
    private Integer idTamanio;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tamanioidTamanio")
    private List<Automovil> automovilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tamanioidTamanio")
    private List<Costoservicio> costoservicioList;

    public Tamanio() {
    }

    public Tamanio(Integer idTamanio) {
        this.idTamanio = idTamanio;
    }

    public Tamanio(Integer idTamanio, String descripcion) {
        this.idTamanio = idTamanio;
        this.descripcion = descripcion;
    }

    public Integer getIdTamanio() {
        return idTamanio;
    }

    public void setIdTamanio(Integer idTamanio) {
        this.idTamanio = idTamanio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Automovil> getAutomovilList() {
        return automovilList;
    }

    public void setAutomovilList(List<Automovil> automovilList) {
        this.automovilList = automovilList;
    }

    @XmlTransient
    public List<Costoservicio> getCostoservicioList() {
        return costoservicioList;
    }

    public void setCostoservicioList(List<Costoservicio> costoservicioList) {
        this.costoservicioList = costoservicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTamanio != null ? idTamanio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tamanio)) {
            return false;
        }
        Tamanio other = (Tamanio) object;
        if ((this.idTamanio == null && other.idTamanio != null) || (this.idTamanio != null && !this.idTamanio.equals(other.idTamanio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Tamanio[ idTamanio=" + idTamanio + " ]";
    }
    
}
