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
@Table(name = "lavador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lavador.findAll", query = "SELECT l FROM Lavador l"),
    @NamedQuery(name = "Lavador.findByIdLavador", query = "SELECT l FROM Lavador l WHERE l.idLavador = :idLavador"),
    @NamedQuery(name = "Lavador.findByComision", query = "SELECT l FROM Lavador l WHERE l.comision = :comision")})
public class Lavador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLavador")
    private Integer idLavador;
    @Basic(optional = false)
    @Column(name = "Comision")
    private double comision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lavadoridLavador")
    private List<Corte> corteList;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lavadoridLavador")
    private List<Ticket> ticketList;

    public Lavador() {
    }

    public Lavador(Integer idLavador) {
        this.idLavador = idLavador;
    }

    public Lavador(Integer idLavador, double comision) {
        this.idLavador = idLavador;
        this.comision = comision;
    }

    public Integer getIdLavador() {
        return idLavador;
    }

    public void setIdLavador(Integer idLavador) {
        this.idLavador = idLavador;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @XmlTransient
    public List<Corte> getCorteList() {
        return corteList;
    }

    public void setCorteList(List<Corte> corteList) {
        this.corteList = corteList;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
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
        hash += (idLavador != null ? idLavador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lavador)) {
            return false;
        }
        Lavador other = (Lavador) object;
        if ((this.idLavador == null && other.idLavador != null) || (this.idLavador != null && !this.idLavador.equals(other.idLavador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Lavador[ idLavador=" + idLavador + " ]";
    }
    
}
