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
import EntityClasses.Costoservicio;
import EntityClasses.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getCostoservicioList() == null) {
            servicio.setCostoservicioList(new ArrayList<Costoservicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Costoservicio> attachedCostoservicioList = new ArrayList<Costoservicio>();
            for (Costoservicio costoservicioListCostoservicioToAttach : servicio.getCostoservicioList()) {
                costoservicioListCostoservicioToAttach = em.getReference(costoservicioListCostoservicioToAttach.getClass(), costoservicioListCostoservicioToAttach.getIdServicioCosto());
                attachedCostoservicioList.add(costoservicioListCostoservicioToAttach);
            }
            servicio.setCostoservicioList(attachedCostoservicioList);
            em.persist(servicio);
            for (Costoservicio costoservicioListCostoservicio : servicio.getCostoservicioList()) {
                Servicio oldServicioidServicioOfCostoservicioListCostoservicio = costoservicioListCostoservicio.getServicioidServicio();
                costoservicioListCostoservicio.setServicioidServicio(servicio);
                costoservicioListCostoservicio = em.merge(costoservicioListCostoservicio);
                if (oldServicioidServicioOfCostoservicioListCostoservicio != null) {
                    oldServicioidServicioOfCostoservicioListCostoservicio.getCostoservicioList().remove(costoservicioListCostoservicio);
                    oldServicioidServicioOfCostoservicioListCostoservicio = em.merge(oldServicioidServicioOfCostoservicioListCostoservicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getIdServicio());
            List<Costoservicio> costoservicioListOld = persistentServicio.getCostoservicioList();
            List<Costoservicio> costoservicioListNew = servicio.getCostoservicioList();
            List<String> illegalOrphanMessages = null;
            for (Costoservicio costoservicioListOldCostoservicio : costoservicioListOld) {
                if (!costoservicioListNew.contains(costoservicioListOldCostoservicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Costoservicio " + costoservicioListOldCostoservicio + " since its servicioidServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Costoservicio> attachedCostoservicioListNew = new ArrayList<Costoservicio>();
            for (Costoservicio costoservicioListNewCostoservicioToAttach : costoservicioListNew) {
                costoservicioListNewCostoservicioToAttach = em.getReference(costoservicioListNewCostoservicioToAttach.getClass(), costoservicioListNewCostoservicioToAttach.getIdServicioCosto());
                attachedCostoservicioListNew.add(costoservicioListNewCostoservicioToAttach);
            }
            costoservicioListNew = attachedCostoservicioListNew;
            servicio.setCostoservicioList(costoservicioListNew);
            servicio = em.merge(servicio);
            for (Costoservicio costoservicioListNewCostoservicio : costoservicioListNew) {
                if (!costoservicioListOld.contains(costoservicioListNewCostoservicio)) {
                    Servicio oldServicioidServicioOfCostoservicioListNewCostoservicio = costoservicioListNewCostoservicio.getServicioidServicio();
                    costoservicioListNewCostoservicio.setServicioidServicio(servicio);
                    costoservicioListNewCostoservicio = em.merge(costoservicioListNewCostoservicio);
                    if (oldServicioidServicioOfCostoservicioListNewCostoservicio != null && !oldServicioidServicioOfCostoservicioListNewCostoservicio.equals(servicio)) {
                        oldServicioidServicioOfCostoservicioListNewCostoservicio.getCostoservicioList().remove(costoservicioListNewCostoservicio);
                        oldServicioidServicioOfCostoservicioListNewCostoservicio = em.merge(oldServicioidServicioOfCostoservicioListNewCostoservicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicio.getIdServicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getIdServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Costoservicio> costoservicioListOrphanCheck = servicio.getCostoservicioList();
            for (Costoservicio costoservicioListOrphanCheckCostoservicio : costoservicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicio (" + servicio + ") cannot be destroyed since the Costoservicio " + costoservicioListOrphanCheckCostoservicio + " in its costoservicioList field has a non-nullable servicioidServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
