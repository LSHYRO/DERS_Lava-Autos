/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan6
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Ticket.findByFecha", query = "SELECT t FROM Ticket t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Ticket.findByHora", query = "SELECT t FROM Ticket t WHERE t.hora = :hora"),
    @NamedQuery(name = "Ticket.findByTotal", query = "SELECT t FROM Ticket t WHERE t.total = :total")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTicket")
    private Integer idTicket;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "Total")
    private double total;
    @JoinColumn(name = "Automovil_idAutomovil", referencedColumnName = "idAutomovil")
    @ManyToOne(optional = false)
    private Automovil automovilidAutomovil;
    @JoinColumn(name = "Lavador_idLavador", referencedColumnName = "idLavador")
    @ManyToOne(optional = false)
    private Lavador lavadoridLavador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketidTicket")
    private List<Serviciosolicitado> serviciosolicitadoList;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, Date fecha, Date hora, double total) {
        this.idTicket = idTicket;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Automovil getAutomovilidAutomovil() {
        return automovilidAutomovil;
    }

    public void setAutomovilidAutomovil(Automovil automovilidAutomovil) {
        this.automovilidAutomovil = automovilidAutomovil;
    }

    public Lavador getLavadoridLavador() {
        return lavadoridLavador;
    }

    public void setLavadoridLavador(Lavador lavadoridLavador) {
        this.lavadoridLavador = lavadoridLavador;
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
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Ticket[ idTicket=" + idTicket + " ]";
    }
    
}
