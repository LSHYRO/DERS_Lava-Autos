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
@Table(name = "corte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corte.findAll", query = "SELECT c FROM Corte c"),
    @NamedQuery(name = "Corte.findByIdCorte", query = "SELECT c FROM Corte c WHERE c.idCorte = :idCorte"),
    @NamedQuery(name = "Corte.findByFecha", query = "SELECT c FROM Corte c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Corte.findByMonto", query = "SELECT c FROM Corte c WHERE c.monto = :monto")})
public class Corte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCorte")
    private Integer idCorte;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Monto")
    private double monto;
    @JoinColumn(name = "Lavador_idLavador", referencedColumnName = "idLavador")
    @ManyToOne(optional = false)
    private Lavador lavadoridLavador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corteidCorte")
    private List<Pago> pagoList;

    public Corte() {
    }

    public Corte(Integer idCorte) {
        this.idCorte = idCorte;
    }

    public Corte(Integer idCorte, Date fecha, double monto) {
        this.idCorte = idCorte;
        this.fecha = fecha;
        this.monto = monto;
    }

    public Integer getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Integer idCorte) {
        this.idCorte = idCorte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Lavador getLavadoridLavador() {
        return lavadoridLavador;
    }

    public void setLavadoridLavador(Lavador lavadoridLavador) {
        this.lavadoridLavador = lavadoridLavador;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorte != null ? idCorte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corte)) {
            return false;
        }
        Corte other = (Corte) object;
        if ((this.idCorte == null && other.idCorte != null) || (this.idCorte != null && !this.idCorte.equals(other.idCorte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Corte[ idCorte=" + idCorte + " ]";
    }
    
}
