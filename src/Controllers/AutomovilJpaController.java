/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import EntityClasses.Automovil;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntityClasses.Tamanio;
import EntityClasses.Tipovehiculo;
import EntityClasses.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class AutomovilJpaController implements Serializable {

    public AutomovilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Automovil automovil) {
        if (automovil.getTicketList() == null) {
            automovil.setTicketList(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tamanio tamanioidTamanio = automovil.getTamanioidTamanio();
            if (tamanioidTamanio != null) {
                tamanioidTamanio = em.getReference(tamanioidTamanio.getClass(), tamanioidTamanio.getIdTamanio());
                automovil.setTamanioidTamanio(tamanioidTamanio);
            }
            Tipovehiculo tipoVehiculoidTipoVehiculo = automovil.getTipoVehiculoidTipoVehiculo();
            if (tipoVehiculoidTipoVehiculo != null) {
                tipoVehiculoidTipoVehiculo = em.getReference(tipoVehiculoidTipoVehiculo.getClass(), tipoVehiculoidTipoVehiculo.getIdTipoVehiculo());
                automovil.setTipoVehiculoidTipoVehiculo(tipoVehiculoidTipoVehiculo);
            }
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : automovil.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getIdTicket());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            automovil.setTicketList(attachedTicketList);
            em.persist(automovil);
            if (tamanioidTamanio != null) {
                tamanioidTamanio.getAutomovilList().add(automovil);
                tamanioidTamanio = em.merge(tamanioidTamanio);
            }
            if (tipoVehiculoidTipoVehiculo != null) {
                tipoVehiculoidTipoVehiculo.getAutomovilList().add(automovil);
                tipoVehiculoidTipoVehiculo = em.merge(tipoVehiculoidTipoVehiculo);
            }
            for (Ticket ticketListTicket : automovil.getTicketList()) {
                Automovil oldAutomovilidAutomovilOfTicketListTicket = ticketListTicket.getAutomovilidAutomovil();
                ticketListTicket.setAutomovilidAutomovil(automovil);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldAutomovilidAutomovilOfTicketListTicket != null) {
                    oldAutomovilidAutomovilOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldAutomovilidAutomovilOfTicketListTicket = em.merge(oldAutomovilidAutomovilOfTicketListTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Automovil automovil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Automovil persistentAutomovil = em.find(Automovil.class, automovil.getIdAutomovil());
            Tamanio tamanioidTamanioOld = persistentAutomovil.getTamanioidTamanio();
            Tamanio tamanioidTamanioNew = automovil.getTamanioidTamanio();
            Tipovehiculo tipoVehiculoidTipoVehiculoOld = persistentAutomovil.getTipoVehiculoidTipoVehiculo();
            Tipovehiculo tipoVehiculoidTipoVehiculoNew = automovil.getTipoVehiculoidTipoVehiculo();
            List<Ticket> ticketListOld = persistentAutomovil.getTicketList();
            List<Ticket> ticketListNew = automovil.getTicketList();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its automovilidAutomovil field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tamanioidTamanioNew != null) {
                tamanioidTamanioNew = em.getReference(tamanioidTamanioNew.getClass(), tamanioidTamanioNew.getIdTamanio());
                automovil.setTamanioidTamanio(tamanioidTamanioNew);
            }
            if (tipoVehiculoidTipoVehiculoNew != null) {
                tipoVehiculoidTipoVehiculoNew = em.getReference(tipoVehiculoidTipoVehiculoNew.getClass(), tipoVehiculoidTipoVehiculoNew.getIdTipoVehiculo());
                automovil.setTipoVehiculoidTipoVehiculo(tipoVehiculoidTipoVehiculoNew);
            }
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getIdTicket());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            automovil.setTicketList(ticketListNew);
            automovil = em.merge(automovil);
            if (tamanioidTamanioOld != null && !tamanioidTamanioOld.equals(tamanioidTamanioNew)) {
                tamanioidTamanioOld.getAutomovilList().remove(automovil);
                tamanioidTamanioOld = em.merge(tamanioidTamanioOld);
            }
            if (tamanioidTamanioNew != null && !tamanioidTamanioNew.equals(tamanioidTamanioOld)) {
                tamanioidTamanioNew.getAutomovilList().add(automovil);
                tamanioidTamanioNew = em.merge(tamanioidTamanioNew);
            }
            if (tipoVehiculoidTipoVehiculoOld != null && !tipoVehiculoidTipoVehiculoOld.equals(tipoVehiculoidTipoVehiculoNew)) {
                tipoVehiculoidTipoVehiculoOld.getAutomovilList().remove(automovil);
                tipoVehiculoidTipoVehiculoOld = em.merge(tipoVehiculoidTipoVehiculoOld);
            }
            if (tipoVehiculoidTipoVehiculoNew != null && !tipoVehiculoidTipoVehiculoNew.equals(tipoVehiculoidTipoVehiculoOld)) {
                tipoVehiculoidTipoVehiculoNew.getAutomovilList().add(automovil);
                tipoVehiculoidTipoVehiculoNew = em.merge(tipoVehiculoidTipoVehiculoNew);
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Automovil oldAutomovilidAutomovilOfTicketListNewTicket = ticketListNewTicket.getAutomovilidAutomovil();
                    ticketListNewTicket.setAutomovilidAutomovil(automovil);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldAutomovilidAutomovilOfTicketListNewTicket != null && !oldAutomovilidAutomovilOfTicketListNewTicket.equals(automovil)) {
                        oldAutomovilidAutomovilOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldAutomovilidAutomovilOfTicketListNewTicket = em.merge(oldAutomovilidAutomovilOfTicketListNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = automovil.getIdAutomovil();
                if (findAutomovil(id) == null) {
                    throw new NonexistentEntityException("The automovil with id " + id + " no longer exists.");
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
            Automovil automovil;
            try {
                automovil = em.getReference(Automovil.class, id);
                automovil.getIdAutomovil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The automovil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ticket> ticketListOrphanCheck = automovil.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Automovil (" + automovil + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable automovilidAutomovil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tamanio tamanioidTamanio = automovil.getTamanioidTamanio();
            if (tamanioidTamanio != null) {
                tamanioidTamanio.getAutomovilList().remove(automovil);
                tamanioidTamanio = em.merge(tamanioidTamanio);
            }
            Tipovehiculo tipoVehiculoidTipoVehiculo = automovil.getTipoVehiculoidTipoVehiculo();
            if (tipoVehiculoidTipoVehiculo != null) {
                tipoVehiculoidTipoVehiculo.getAutomovilList().remove(automovil);
                tipoVehiculoidTipoVehiculo = em.merge(tipoVehiculoidTipoVehiculo);
            }
            em.remove(automovil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Automovil> findAutomovilEntities() {
        return findAutomovilEntities(true, -1, -1);
    }

    public List<Automovil> findAutomovilEntities(int maxResults, int firstResult) {
        return findAutomovilEntities(false, maxResults, firstResult);
    }

    private List<Automovil> findAutomovilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Automovil.class));
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

    public Automovil findAutomovil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Automovil.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutomovilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Automovil> rt = cq.from(Automovil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
