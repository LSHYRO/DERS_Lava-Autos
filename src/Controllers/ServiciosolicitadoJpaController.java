/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntityClasses.Costoservicio;
import EntityClasses.Serviciosolicitado;
import EntityClasses.Ticket;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class ServiciosolicitadoJpaController implements Serializable {

    public ServiciosolicitadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Serviciosolicitado serviciosolicitado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Costoservicio costoServicioidServicioCosto = serviciosolicitado.getCostoServicioidServicioCosto();
            if (costoServicioidServicioCosto != null) {
                costoServicioidServicioCosto = em.getReference(costoServicioidServicioCosto.getClass(), costoServicioidServicioCosto.getIdServicioCosto());
                serviciosolicitado.setCostoServicioidServicioCosto(costoServicioidServicioCosto);
            }
            Ticket ticketidTicket = serviciosolicitado.getTicketidTicket();
            if (ticketidTicket != null) {
                ticketidTicket = em.getReference(ticketidTicket.getClass(), ticketidTicket.getIdTicket());
                serviciosolicitado.setTicketidTicket(ticketidTicket);
            }
            em.persist(serviciosolicitado);
            if (costoServicioidServicioCosto != null) {
                costoServicioidServicioCosto.getServiciosolicitadoList().add(serviciosolicitado);
                costoServicioidServicioCosto = em.merge(costoServicioidServicioCosto);
            }
            if (ticketidTicket != null) {
                ticketidTicket.getServiciosolicitadoList().add(serviciosolicitado);
                ticketidTicket = em.merge(ticketidTicket);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Serviciosolicitado serviciosolicitado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Serviciosolicitado persistentServiciosolicitado = em.find(Serviciosolicitado.class, serviciosolicitado.getIdServicioSolicitado());
            Costoservicio costoServicioidServicioCostoOld = persistentServiciosolicitado.getCostoServicioidServicioCosto();
            Costoservicio costoServicioidServicioCostoNew = serviciosolicitado.getCostoServicioidServicioCosto();
            Ticket ticketidTicketOld = persistentServiciosolicitado.getTicketidTicket();
            Ticket ticketidTicketNew = serviciosolicitado.getTicketidTicket();
            if (costoServicioidServicioCostoNew != null) {
                costoServicioidServicioCostoNew = em.getReference(costoServicioidServicioCostoNew.getClass(), costoServicioidServicioCostoNew.getIdServicioCosto());
                serviciosolicitado.setCostoServicioidServicioCosto(costoServicioidServicioCostoNew);
            }
            if (ticketidTicketNew != null) {
                ticketidTicketNew = em.getReference(ticketidTicketNew.getClass(), ticketidTicketNew.getIdTicket());
                serviciosolicitado.setTicketidTicket(ticketidTicketNew);
            }
            serviciosolicitado = em.merge(serviciosolicitado);
            if (costoServicioidServicioCostoOld != null && !costoServicioidServicioCostoOld.equals(costoServicioidServicioCostoNew)) {
                costoServicioidServicioCostoOld.getServiciosolicitadoList().remove(serviciosolicitado);
                costoServicioidServicioCostoOld = em.merge(costoServicioidServicioCostoOld);
            }
            if (costoServicioidServicioCostoNew != null && !costoServicioidServicioCostoNew.equals(costoServicioidServicioCostoOld)) {
                costoServicioidServicioCostoNew.getServiciosolicitadoList().add(serviciosolicitado);
                costoServicioidServicioCostoNew = em.merge(costoServicioidServicioCostoNew);
            }
            if (ticketidTicketOld != null && !ticketidTicketOld.equals(ticketidTicketNew)) {
                ticketidTicketOld.getServiciosolicitadoList().remove(serviciosolicitado);
                ticketidTicketOld = em.merge(ticketidTicketOld);
            }
            if (ticketidTicketNew != null && !ticketidTicketNew.equals(ticketidTicketOld)) {
                ticketidTicketNew.getServiciosolicitadoList().add(serviciosolicitado);
                ticketidTicketNew = em.merge(ticketidTicketNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serviciosolicitado.getIdServicioSolicitado();
                if (findServiciosolicitado(id) == null) {
                    throw new NonexistentEntityException("The serviciosolicitado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Serviciosolicitado serviciosolicitado;
            try {
                serviciosolicitado = em.getReference(Serviciosolicitado.class, id);
                serviciosolicitado.getIdServicioSolicitado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviciosolicitado with id " + id + " no longer exists.", enfe);
            }
            Costoservicio costoServicioidServicioCosto = serviciosolicitado.getCostoServicioidServicioCosto();
            if (costoServicioidServicioCosto != null) {
                costoServicioidServicioCosto.getServiciosolicitadoList().remove(serviciosolicitado);
                costoServicioidServicioCosto = em.merge(costoServicioidServicioCosto);
            }
            Ticket ticketidTicket = serviciosolicitado.getTicketidTicket();
            if (ticketidTicket != null) {
                ticketidTicket.getServiciosolicitadoList().remove(serviciosolicitado);
                ticketidTicket = em.merge(ticketidTicket);
            }
            em.remove(serviciosolicitado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Serviciosolicitado> findServiciosolicitadoEntities() {
        return findServiciosolicitadoEntities(true, -1, -1);
    }

    public List<Serviciosolicitado> findServiciosolicitadoEntities(int maxResults, int firstResult) {
        return findServiciosolicitadoEntities(false, maxResults, firstResult);
    }

    private List<Serviciosolicitado> findServiciosolicitadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Serviciosolicitado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Serviciosolicitado findServiciosolicitado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Serviciosolicitado.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosolicitadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Serviciosolicitado> rt = cq.from(Serviciosolicitado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
