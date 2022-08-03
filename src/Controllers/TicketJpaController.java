/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntityClasses.Automovil;
import EntityClasses.Lavador;
import EntityClasses.Serviciosolicitado;
import EntityClasses.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        if (ticket.getServiciosolicitadoList() == null) {
            ticket.setServiciosolicitadoList(new ArrayList<Serviciosolicitado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Automovil automovilidAutomovil = ticket.getAutomovilidAutomovil();
            if (automovilidAutomovil != null) {
                automovilidAutomovil = em.getReference(automovilidAutomovil.getClass(), automovilidAutomovil.getIdAutomovil());
                ticket.setAutomovilidAutomovil(automovilidAutomovil);
            }
            Lavador lavadoridLavador = ticket.getLavadoridLavador();
            if (lavadoridLavador != null) {
                lavadoridLavador = em.getReference(lavadoridLavador.getClass(), lavadoridLavador.getIdLavador());
                ticket.setLavadoridLavador(lavadoridLavador);
            }
            List<Serviciosolicitado> attachedServiciosolicitadoList = new ArrayList<Serviciosolicitado>();
            for (Serviciosolicitado serviciosolicitadoListServiciosolicitadoToAttach : ticket.getServiciosolicitadoList()) {
                serviciosolicitadoListServiciosolicitadoToAttach = em.getReference(serviciosolicitadoListServiciosolicitadoToAttach.getClass(), serviciosolicitadoListServiciosolicitadoToAttach.getIdServicioSolicitado());
                attachedServiciosolicitadoList.add(serviciosolicitadoListServiciosolicitadoToAttach);
            }
            ticket.setServiciosolicitadoList(attachedServiciosolicitadoList);
            em.persist(ticket);
            if (automovilidAutomovil != null) {
                automovilidAutomovil.getTicketList().add(ticket);
                automovilidAutomovil = em.merge(automovilidAutomovil);
            }
            if (lavadoridLavador != null) {
                lavadoridLavador.getTicketList().add(ticket);
                lavadoridLavador = em.merge(lavadoridLavador);
            }
            for (Serviciosolicitado serviciosolicitadoListServiciosolicitado : ticket.getServiciosolicitadoList()) {
                Ticket oldTicketidTicketOfServiciosolicitadoListServiciosolicitado = serviciosolicitadoListServiciosolicitado.getTicketidTicket();
                serviciosolicitadoListServiciosolicitado.setTicketidTicket(ticket);
                serviciosolicitadoListServiciosolicitado = em.merge(serviciosolicitadoListServiciosolicitado);
                if (oldTicketidTicketOfServiciosolicitadoListServiciosolicitado != null) {
                    oldTicketidTicketOfServiciosolicitadoListServiciosolicitado.getServiciosolicitadoList().remove(serviciosolicitadoListServiciosolicitado);
                    oldTicketidTicketOfServiciosolicitadoListServiciosolicitado = em.merge(oldTicketidTicketOfServiciosolicitadoListServiciosolicitado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getIdTicket());
            Automovil automovilidAutomovilOld = persistentTicket.getAutomovilidAutomovil();
            Automovil automovilidAutomovilNew = ticket.getAutomovilidAutomovil();
            Lavador lavadoridLavadorOld = persistentTicket.getLavadoridLavador();
            Lavador lavadoridLavadorNew = ticket.getLavadoridLavador();
            List<Serviciosolicitado> serviciosolicitadoListOld = persistentTicket.getServiciosolicitadoList();
            List<Serviciosolicitado> serviciosolicitadoListNew = ticket.getServiciosolicitadoList();
            List<String> illegalOrphanMessages = null;
            for (Serviciosolicitado serviciosolicitadoListOldServiciosolicitado : serviciosolicitadoListOld) {
                if (!serviciosolicitadoListNew.contains(serviciosolicitadoListOldServiciosolicitado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Serviciosolicitado " + serviciosolicitadoListOldServiciosolicitado + " since its ticketidTicket field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (automovilidAutomovilNew != null) {
                automovilidAutomovilNew = em.getReference(automovilidAutomovilNew.getClass(), automovilidAutomovilNew.getIdAutomovil());
                ticket.setAutomovilidAutomovil(automovilidAutomovilNew);
            }
            if (lavadoridLavadorNew != null) {
                lavadoridLavadorNew = em.getReference(lavadoridLavadorNew.getClass(), lavadoridLavadorNew.getIdLavador());
                ticket.setLavadoridLavador(lavadoridLavadorNew);
            }
            List<Serviciosolicitado> attachedServiciosolicitadoListNew = new ArrayList<Serviciosolicitado>();
            for (Serviciosolicitado serviciosolicitadoListNewServiciosolicitadoToAttach : serviciosolicitadoListNew) {
                serviciosolicitadoListNewServiciosolicitadoToAttach = em.getReference(serviciosolicitadoListNewServiciosolicitadoToAttach.getClass(), serviciosolicitadoListNewServiciosolicitadoToAttach.getIdServicioSolicitado());
                attachedServiciosolicitadoListNew.add(serviciosolicitadoListNewServiciosolicitadoToAttach);
            }
            serviciosolicitadoListNew = attachedServiciosolicitadoListNew;
            ticket.setServiciosolicitadoList(serviciosolicitadoListNew);
            ticket = em.merge(ticket);
            if (automovilidAutomovilOld != null && !automovilidAutomovilOld.equals(automovilidAutomovilNew)) {
                automovilidAutomovilOld.getTicketList().remove(ticket);
                automovilidAutomovilOld = em.merge(automovilidAutomovilOld);
            }
            if (automovilidAutomovilNew != null && !automovilidAutomovilNew.equals(automovilidAutomovilOld)) {
                automovilidAutomovilNew.getTicketList().add(ticket);
                automovilidAutomovilNew = em.merge(automovilidAutomovilNew);
            }
            if (lavadoridLavadorOld != null && !lavadoridLavadorOld.equals(lavadoridLavadorNew)) {
                lavadoridLavadorOld.getTicketList().remove(ticket);
                lavadoridLavadorOld = em.merge(lavadoridLavadorOld);
            }
            if (lavadoridLavadorNew != null && !lavadoridLavadorNew.equals(lavadoridLavadorOld)) {
                lavadoridLavadorNew.getTicketList().add(ticket);
                lavadoridLavadorNew = em.merge(lavadoridLavadorNew);
            }
            for (Serviciosolicitado serviciosolicitadoListNewServiciosolicitado : serviciosolicitadoListNew) {
                if (!serviciosolicitadoListOld.contains(serviciosolicitadoListNewServiciosolicitado)) {
                    Ticket oldTicketidTicketOfServiciosolicitadoListNewServiciosolicitado = serviciosolicitadoListNewServiciosolicitado.getTicketidTicket();
                    serviciosolicitadoListNewServiciosolicitado.setTicketidTicket(ticket);
                    serviciosolicitadoListNewServiciosolicitado = em.merge(serviciosolicitadoListNewServiciosolicitado);
                    if (oldTicketidTicketOfServiciosolicitadoListNewServiciosolicitado != null && !oldTicketidTicketOfServiciosolicitadoListNewServiciosolicitado.equals(ticket)) {
                        oldTicketidTicketOfServiciosolicitadoListNewServiciosolicitado.getServiciosolicitadoList().remove(serviciosolicitadoListNewServiciosolicitado);
                        oldTicketidTicketOfServiciosolicitadoListNewServiciosolicitado = em.merge(oldTicketidTicketOfServiciosolicitadoListNewServiciosolicitado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getIdTicket();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Serviciosolicitado> serviciosolicitadoListOrphanCheck = ticket.getServiciosolicitadoList();
            for (Serviciosolicitado serviciosolicitadoListOrphanCheckServiciosolicitado : serviciosolicitadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ticket (" + ticket + ") cannot be destroyed since the Serviciosolicitado " + serviciosolicitadoListOrphanCheckServiciosolicitado + " in its serviciosolicitadoList field has a non-nullable ticketidTicket field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Automovil automovilidAutomovil = ticket.getAutomovilidAutomovil();
            if (automovilidAutomovil != null) {
                automovilidAutomovil.getTicketList().remove(ticket);
                automovilidAutomovil = em.merge(automovilidAutomovil);
            }
            Lavador lavadoridLavador = ticket.getLavadoridLavador();
            if (lavadoridLavador != null) {
                lavadoridLavador.getTicketList().remove(ticket);
                lavadoridLavador = em.merge(lavadoridLavador);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
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

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
