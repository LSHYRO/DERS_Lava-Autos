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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "costoservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Costoservicio.findAll", query = "SELECT c FROM Costoservicio c"),
    @NamedQuery(name = "Costoservicio.findByIdServicioCosto", query = "SELECT c FROM Costoservicio c WHERE c.idServicioCosto = :idServicioCosto"),
    @NamedQuery(name = "Costoservicio.findByPrecio", query = "SELECT c FROM Costoservicio c WHERE c.precio = :precio")})
public class Costoservicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServicioCosto")
    private Integer idServicioCosto;
    @Basic(optional = false)
    @Column(name = "Precio")
    private double precio;
    @JoinColumn(name = "Servicio_idServicio", referencedColumnName = "idServicio")
    @ManyToOne(optional = false)
    private Servicio servicioidServicio;
    @JoinColumn(name = "Tamanio_idTamanio", referencedColumnName = "idTamanio")
    @ManyToOne(optional = false)
    private Tamanio tamanioidTamanio;
    @JoinColumn(name = "TipoVehiculo_idTipoVehiculo", referencedColumnName = "idTipoVehiculo")
    @ManyToOne
    private Tipovehiculo tipoVehiculoidTipoVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "costoServicioidServicioCosto")
    private List<Serviciosolicitado> serviciosolicitadoList;

    public Costoservicio() {
    }

    public Costoservicio(Integer idServicioCosto) {
        this.idServicioCosto = idServicioCosto;
    }

    public Costoservicio(Integer idServicioCosto, double precio) {
        this.idServicioCosto = idServicioCosto;
        this.precio = precio;
    }

    public Integer getIdServicioCosto() {
        return idServicioCosto;
    }

    public void setIdServicioCosto(Integer idServicioCosto) {
        this.idServicioCosto = idServicioCosto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Servicio getServicioidServicio() {
        return servicioidServicio;
    }

    public void setServicioidServicio(Servicio servicioidServicio) {
        this.servicioidServicio = servicioidServicio;
    }

    public Tamanio getTamanioidTamanio() {
        return tamanioidTamanio;
    }

    public void setTamanioidTamanio(Tamanio tamanioidTamanio) {
        this.tamanioidTamanio = tamanioidTamanio;
    }

    public Tipovehiculo getTipoVehiculoidTipoVehiculo() {
        return tipoVehiculoidTipoVehiculo;
    }

    public void setTipoVehiculoidTipoVehiculo(Tipovehiculo tipoVehiculoidTipoVehiculo) {
        this.tipoVehiculoidTipoVehiculo = tipoVehiculoidTipoVehiculo;
    }

    @XmlTransient
    public List<Serviciosolicitado> getServiciosolicitadoList() {
        return serviciosolicitadoList;
    }

    public void setServiciosolicitadoList(List<Serviciosolicitado> serviciosolicitadoList) {
        this.serviciosolicitadoList = serviciosolicitadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicioCosto != null ? idServicioCosto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Costoservicio)) {
            return false;
        }
        Costoservicio other = (Costoservicio) object;
        if ((this.idServicioCosto == null && other.idServicioCosto != null) || (this.idServicioCosto != null && !this.idServicioCosto.equals(other.idServicioCosto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Costoservicio[ idServicioCosto=" + idServicioCosto + " ]";
    }
    
}
