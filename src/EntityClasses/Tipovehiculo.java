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
@Table(name = "tipovehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipovehiculo.findAll", query = "SELECT t FROM Tipovehiculo t"),
    @NamedQuery(name = "Tipovehiculo.findByIdTipoVehiculo", query = "SELECT t FROM Tipovehiculo t WHERE t.idTipoVehiculo = :idTipoVehiculo"),
    @NamedQuery(name = "Tipovehiculo.findByDescripcion", query = "SELECT t FROM Tipovehiculo t WHERE t.descripcion = :descripcion")})
public class Tipovehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoVehiculo")
    private Integer idTipoVehiculo;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoVehiculoidTipoVehiculo")
    private List<Automovil> automovilList;
    @OneToMany(mappedBy = "tipoVehiculoidTipoVehiculo")
    private List<Costoservicio> costoservicioList;

    public Tipovehiculo() {
    }

    public Tipovehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public Tipovehiculo(Integer idTipoVehiculo, String descripcion) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
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
        hash += (idTipoVehiculo != null ? idTipoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipovehiculo)) {
            return false;
        }
        Tipovehiculo other = (Tipovehiculo) object;
        if ((this.idTipoVehiculo == null && other.idTipoVehiculo != null) || (this.idTipoVehiculo != null && !this.idTipoVehiculo.equals(other.idTipoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Tipovehiculo[ idTipoVehiculo=" + idTipoVehiculo + " ]";
    }
    
}
