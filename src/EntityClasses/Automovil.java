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
@Table(name = "automovil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Automovil.findAll", query = "SELECT a FROM Automovil a"),
    @NamedQuery(name = "Automovil.findByIdAutomovil", query = "SELECT a FROM Automovil a WHERE a.idAutomovil = :idAutomovil"),
    @NamedQuery(name = "Automovil.findByNombreCliente", query = "SELECT a FROM Automovil a WHERE a.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "Automovil.findByMarca", query = "SELECT a FROM Automovil a WHERE a.marca = :marca"),
    @NamedQuery(name = "Automovil.findByModelo", query = "SELECT a FROM Automovil a WHERE a.modelo = :modelo"),
    @NamedQuery(name = "Automovil.findByColor", query = "SELECT a FROM Automovil a WHERE a.color = :color"),
    @NamedQuery(name = "Automovil.findByPlacas", query = "SELECT a FROM Automovil a WHERE a.placas = :placas")})
public class Automovil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAutomovil")
    private Integer idAutomovil;
    @Column(name = "NombreCliente")
    private String nombreCliente;
    @Column(name = "Marca")
    private String marca;
    @Column(name = "Modelo")
    private String modelo;
    @Column(name = "Color")
    private String color;
    @Column(name = "Placas")
    private String placas;
    @JoinColumn(name = "Tamanio_idTamanio", referencedColumnName = "idTamanio")
    @ManyToOne(optional = false)
    private Tamanio tamanioidTamanio;
    @JoinColumn(name = "TipoVehiculo_idTipoVehiculo", referencedColumnName = "idTipoVehiculo")
    @ManyToOne(optional = false)
    private Tipovehiculo tipoVehiculoidTipoVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "automovilidAutomovil")
    private List<Ticket> ticketList;

    public Automovil() {
    }

    public Automovil(Integer idAutomovil) {
        this.idAutomovil = idAutomovil;
    }

    public Integer getIdAutomovil() {
        return idAutomovil;
    }

    public void setIdAutomovil(Integer idAutomovil) {
        this.idAutomovil = idAutomovil;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
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
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutomovil != null ? idAutomovil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Automovil)) {
            return false;
        }
        Automovil other = (Automovil) object;
        if ((this.idAutomovil == null && other.idAutomovil != null) || (this.idAutomovil != null && !this.idAutomovil.equals(other.idAutomovil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Automovil[ idAutomovil=" + idAutomovil + " ]";
    }
    
}
