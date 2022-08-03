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
import EntityClasses.Usuario;
import EntityClasses.Corte;
import EntityClasses.Lavador;
import java.util.ArrayList;
import java.util.List;
import EntityClasses.Ticket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class LavadorJpaController implements Serializable {

    public LavadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lavador lavador) {
        if (lavador.getCorteList() == null) {
            lavador.setCorteList(new ArrayList<Corte>());
        }
        if (lavador.getTicketList() == null) {
            lavador.setTicketList(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioidUsuario = lavador.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
                lavador.setUsuarioidUsuario(usuarioidUsuario);
            }
            List<Corte> attachedCorteList = new ArrayList<Corte>();
            for (Corte corteListCorteToAttach : lavador.getCorteList()) {
                corteListCorteToAttach = em.getReference(corteListCorteToAttach.getClass(), corteListCorteToAttach.getIdCorte());
                attachedCorteList.add(corteListCorteToAttach);
            }
            lavador.setCorteList(attachedCorteList);
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : lavador.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getIdTicket());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            lavador.setTicketList(attachedTicketList);
            em.persist(lavador);
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getLavadorList().add(lavador);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            for (Corte corteListCorte : lavador.getCorteList()) {
                Lavador oldLavadoridLavadorOfCorteListCorte = corteListCorte.getLavadoridLavador();
                corteListCorte.setLavadoridLavador(lavador);
                corteListCorte = em.merge(corteListCorte);
                if (oldLavadoridLavadorOfCorteListCorte != null) {
                    oldLavadoridLavadorOfCorteListCorte.getCorteList().remove(corteListCorte);
                    oldLavadoridLavadorOfCorteListCorte = em.merge(oldLavadoridLavadorOfCorteListCorte);
                }
            }
            for (Ticket ticketListTicket : lavador.getTicketList()) {
                Lavador oldLavadoridLavadorOfTicketListTicket = ticketListTicket.getLavadoridLavador();
                ticketListTicket.setLavadoridLavador(lavador);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldLavadoridLavadorOfTicketListTicket != null) {
                    oldLavadoridLavadorOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldLavadoridLavadorOfTicketListTicket = em.merge(oldLavadoridLavadorOfTicketListTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lavador lavador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lavador persistentLavador = em.find(Lavador.class, lavador.getIdLavador());
            Usuario usuarioidUsuarioOld = persistentLavador.getUsuarioidUsuario();
            Usuario usuarioidUsuarioNew = lavador.getUsuarioidUsuario();
            List<Corte> corteListOld = persistentLavador.getCorteList();
            List<Corte> corteListNew = lavador.getCorteList();
            List<Ticket> ticketListOld = persistentLavador.getTicketList();
            List<Ticket> ticketListNew = lavador.getTicketList();
            List<String> illegalOrphanMessages = null;
            for (Corte corteListOldCorte : corteListOld) {
                if (!corteListNew.contains(corteListOldCorte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Corte " + corteListOldCorte + " since its lavadoridLavador field is not nullable.");
                }
            }
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its lavadoridLavador field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioidUsuarioNew != null) {
                usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
                lavador.setUsuarioidUsuario(usuarioidUsuarioNew);
            }
            List<Corte> attachedCorteListNew = new ArrayList<Corte>();
            for (Corte corteListNewCorteToAttach : corteListNew) {
                corteListNewCorteToAttach = em.getReference(corteListNewCorteToAttach.getClass(), corteListNewCorteToAttach.getIdCorte());
                attachedCorteListNew.add(corteListNewCorteToAttach);
            }
            corteListNew = attachedCorteListNew;
            lavador.setCorteList(corteListNew);
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getIdTicket());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            lavador.setTicketList(ticketListNew);
            lavador = em.merge(lavador);
            if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
                usuarioidUsuarioOld.getLavadorList().remove(lavador);
                usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
            }
            if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
                usuarioidUsuarioNew.getLavadorList().add(lavador);
                usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
            }
            for (Corte corteListNewCorte : corteListNew) {
                if (!corteListOld.contains(corteListNewCorte)) {
                    Lavador oldLavadoridLavadorOfCorteListNewCorte = corteListNewCorte.getLavadoridLavador();
                    corteListNewCorte.setLavadoridLavador(lavador);
                    corteListNewCorte = em.merge(corteListNewCorte);
                    if (oldLavadoridLavadorOfCorteListNewCorte != null && !oldLavadoridLavadorOfCorteListNewCorte.equals(lavador)) {
                        oldLavadoridLavadorOfCorteListNewCorte.getCorteList().remove(corteListNewCorte);
                        oldLavadoridLavadorOfCorteListNewCorte = em.merge(oldLavadoridLavadorOfCorteListNewCorte);
                    }
                }
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Lavador oldLavadoridLavadorOfTicketListNewTicket = ticketListNewTicket.getLavadoridLavador();
                    ticketListNewTicket.setLavadoridLavador(lavador);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldLavadoridLavadorOfTicketListNewTicket != null && !oldLavadoridLavadorOfTicketListNewTicket.equals(lavador)) {
                        oldLavadoridLavadorOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldLavadoridLavadorOfTicketListNewTicket = em.merge(oldLavadoridLavadorOfTicketListNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lavador.getIdLavador();
                if (findLavador(id) == null) {
                    throw new NonexistentEntityException("The lavador with id " + id + " no longer exists.");
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
            Lavador lavador;
            try {
                lavador = em.getReference(Lavador.class, id);
                lavador.getIdLavador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lavador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Corte> corteListOrphanCheck = lavador.getCorteList();
            for (Corte corteListOrphanCheckCorte : corteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lavador (" + lavador + ") cannot be destroyed since the Corte " + corteListOrphanCheckCorte + " in its corteList field has a non-nullable lavadoridLavador field.");
            }
            List<Ticket> ticketListOrphanCheck = lavador.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lavador (" + lavador + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable lavadoridLavador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioidUsuario = lavador.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getLavadorList().remove(lavador);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.remove(lavador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lavador> findLavadorEntities() {
        return findLavadorEntities(true, -1, -1);
    }

    public List<Lavador> findLavadorEntities(int maxResults, int firstResult) {
        return findLavadorEntities(false, maxResults, firstResult);
    }

    private List<Lavador> findLavadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lavador.class));
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

    public Lavador findLavador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lavador.class, id);
        } finally {
            em.close();
        }
    }

    public int getLavadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lavador> rt = cq.from(Lavador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
