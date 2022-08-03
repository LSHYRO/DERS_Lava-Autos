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
import EntityClasses.Corte;
import EntityClasses.Pago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class PagoJpaController implements Serializable {

    public PagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pago pago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corte corteidCorte = pago.getCorteidCorte();
            if (corteidCorte != null) {
                corteidCorte = em.getReference(corteidCorte.getClass(), corteidCorte.getIdCorte());
                pago.setCorteidCorte(corteidCorte);
            }
            em.persist(pago);
            if (corteidCorte != null) {
                corteidCorte.getPagoList().add(pago);
                corteidCorte = em.merge(corteidCorte);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getIdPago());
            Corte corteidCorteOld = persistentPago.getCorteidCorte();
            Corte corteidCorteNew = pago.getCorteidCorte();
            if (corteidCorteNew != null) {
                corteidCorteNew = em.getReference(corteidCorteNew.getClass(), corteidCorteNew.getIdCorte());
                pago.setCorteidCorte(corteidCorteNew);
            }
            pago = em.merge(pago);
            if (corteidCorteOld != null && !corteidCorteOld.equals(corteidCorteNew)) {
                corteidCorteOld.getPagoList().remove(pago);
                corteidCorteOld = em.merge(corteidCorteOld);
            }
            if (corteidCorteNew != null && !corteidCorteNew.equals(corteidCorteOld)) {
                corteidCorteNew.getPagoList().add(pago);
                corteidCorteNew = em.merge(corteidCorteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pago.getIdPago();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
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
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getIdPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            Corte corteidCorte = pago.getCorteidCorte();
            if (corteidCorte != null) {
                corteidCorte.getPagoList().remove(pago);
                corteidCorte = em.merge(corteidCorte);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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

    public Pago findPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
