/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import EntityClasses.Corte;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntityClasses.Lavador;
import EntityClasses.Pago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class CorteJpaController implements Serializable {

    public CorteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Corte corte) {
        if (corte.getPagoList() == null) {
            corte.setPagoList(new ArrayList<Pago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lavador lavadoridLavador = corte.getLavadoridLavador();
            if (lavadoridLavador != null) {
                lavadoridLavador = em.getReference(lavadoridLavador.getClass(), lavadoridLavador.getIdLavador());
                corte.setLavadoridLavador(lavadoridLavador);
            }
            List<Pago> attachedPagoList = new ArrayList<Pago>();
            for (Pago pagoListPagoToAttach : corte.getPagoList()) {
                pagoListPagoToAttach = em.getReference(pagoListPagoToAttach.getClass(), pagoListPagoToAttach.getIdPago());
                attachedPagoList.add(pagoListPagoToAttach);
            }
            corte.setPagoList(attachedPagoList);
            em.persist(corte);
            if (lavadoridLavador != null) {
                lavadoridLavador.getCorteList().add(corte);
                lavadoridLavador = em.merge(lavadoridLavador);
            }
            for (Pago pagoListPago : corte.getPagoList()) {
                Corte oldCorteidCorteOfPagoListPago = pagoListPago.getCorteidCorte();
                pagoListPago.setCorteidCorte(corte);
                pagoListPago = em.merge(pagoListPago);
                if (oldCorteidCorteOfPagoListPago != null) {
                    oldCorteidCorteOfPagoListPago.getPagoList().remove(pagoListPago);
                    oldCorteidCorteOfPagoListPago = em.merge(oldCorteidCorteOfPagoListPago);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Corte corte) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corte persistentCorte = em.find(Corte.class, corte.getIdCorte());
            Lavador lavadoridLavadorOld = persistentCorte.getLavadoridLavador();
            Lavador lavadoridLavadorNew = corte.getLavadoridLavador();
            List<Pago> pagoListOld = persistentCorte.getPagoList();
            List<Pago> pagoListNew = corte.getPagoList();
            List<String> illegalOrphanMessages = null;
            for (Pago pagoListOldPago : pagoListOld) {
                if (!pagoListNew.contains(pagoListOldPago)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pago " + pagoListOldPago + " since its corteidCorte field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (lavadoridLavadorNew != null) {
                lavadoridLavadorNew = em.getReference(lavadoridLavadorNew.getClass(), lavadoridLavadorNew.getIdLavador());
                corte.setLavadoridLavador(lavadoridLavadorNew);
            }
            List<Pago> attachedPagoListNew = new ArrayList<Pago>();
            for (Pago pagoListNewPagoToAttach : pagoListNew) {
                pagoListNewPagoToAttach = em.getReference(pagoListNewPagoToAttach.getClass(), pagoListNewPagoToAttach.getIdPago());
                attachedPagoListNew.add(pagoListNewPagoToAttach);
            }
            pagoListNew = attachedPagoListNew;
            corte.setPagoList(pagoListNew);
            corte = em.merge(corte);
            if (lavadoridLavadorOld != null && !lavadoridLavadorOld.equals(lavadoridLavadorNew)) {
                lavadoridLavadorOld.getCorteList().remove(corte);
                lavadoridLavadorOld = em.merge(lavadoridLavadorOld);
            }
            if (lavadoridLavadorNew != null && !lavadoridLavadorNew.equals(lavadoridLavadorOld)) {
                lavadoridLavadorNew.getCorteList().add(corte);
                lavadoridLavadorNew = em.merge(lavadoridLavadorNew);
            }
            for (Pago pagoListNewPago : pagoListNew) {
                if (!pagoListOld.contains(pagoListNewPago)) {
                    Corte oldCorteidCorteOfPagoListNewPago = pagoListNewPago.getCorteidCorte();
                    pagoListNewPago.setCorteidCorte(corte);
                    pagoListNewPago = em.merge(pagoListNewPago);
                    if (oldCorteidCorteOfPagoListNewPago != null && !oldCorteidCorteOfPagoListNewPago.equals(corte)) {
                        oldCorteidCorteOfPagoListNewPago.getPagoList().remove(pagoListNewPago);
                        oldCorteidCorteOfPagoListNewPago = em.merge(oldCorteidCorteOfPagoListNewPago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = corte.getIdCorte();
                if (findCorte(id) == null) {
                    throw new NonexistentEntityException("The corte with id " + id + " no longer exists.");
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
            Corte corte;
            try {
                corte = em.getReference(Corte.class, id);
                corte.getIdCorte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The corte with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pago> pagoListOrphanCheck = corte.getPagoList();
            for (Pago pagoListOrphanCheckPago : pagoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Corte (" + corte + ") cannot be destroyed since the Pago " + pagoListOrphanCheckPago + " in its pagoList field has a non-nullable corteidCorte field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Lavador lavadoridLavador = corte.getLavadoridLavador();
            if (lavadoridLavador != null) {
                lavadoridLavador.getCorteList().remove(corte);
                lavadoridLavador = em.merge(lavadoridLavador);
            }
            em.remove(corte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Corte> findCorteEntities() {
        return findCorteEntities(true, -1, -1);
    }

    public List<Corte> findCorteEntities(int maxResults, int firstResult) {
        return findCorteEntities(false, maxResults, firstResult);
    }

    private List<Corte> findCorteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Corte.class));
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

    public Corte findCorte(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Corte.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Corte> rt = cq.from(Corte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
