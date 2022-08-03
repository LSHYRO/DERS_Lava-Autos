/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan6
 */
@Entity
@Table(name = "serviciosolicitado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serviciosolicitado.findAll", query = "SELECT s FROM Serviciosolicitado s"),
    @NamedQuery(name = "Serviciosolicitado.findByIdServicioSolicitado", query = "SELECT s FROM Serviciosolicitado s WHERE s.idServicioSolicitado = :idServicioSolicitado")})
public class Serviciosolicitado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServicioSolicitado")
    private Integer idServicioSolicitado;
    @JoinColumn(name = "CostoServicio_idServicioCosto", referencedColumnName = "idServicioCosto")
    @ManyToOne(optional = false)
    private Costoservicio costoServicioidServicioCosto;
    @JoinColumn(name = "Ticket_idTicket", referencedColumnName = "idTicket")
    @ManyToOne(optional = false)
    private Ticket ticketidTicket;

    public Serviciosolicitado() {
    }

    public Serviciosolicitado(Integer idServicioSolicitado) {
        this.idServicioSolicitado = idServicioSolicitado;
    }

    public Integer getIdServicioSolicitado() {
        return idServicioSolicitado;
    }

    public void setIdServicioSolicitado(Integer idServicioSolicitado) {
        this.idServicioSolicitado = idServicioSolicitado;
    }

    public Costoservicio getCostoServicioidServicioCosto() {
        return costoServicioidServicioCosto;
    }

    public void setCostoServicioidServicioCosto(Costoservicio costoServicioidServicioCosto) {
        this.costoServicioidServicioCosto = costoServicioidServicioCosto;
    }

    public Ticket getTicketidTicket() {
        return ticketidTicket;
    }

    public void setTicketidTicket(Ticket ticketidTicket) {
        this.ticketidTicket = ticketidTicket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicioSolicitado != null ? idServicioSolicitado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serviciosolicitado)) {
            return false;
        }
        Serviciosolicitado other = (Serviciosolicitado) object;
        if ((this.idServicioSolicitado == null && other.idServicioSolicitado != null) || (this.idServicioSolicitado != null && !this.idServicioSolicitado.equals(other.idServicioSolicitado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Serviciosolicitado[ idServicioSolicitado=" + idServicioSolicitado + " ]";
    }
    
}
