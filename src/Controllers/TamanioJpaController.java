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
import java.util.ArrayList;
import java.util.List;
import EntityClasses.Costoservicio;
import EntityClasses.Tamanio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class TamanioJpaController implements Serializable {

    public TamanioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tamanio tamanio) {
        if (tamanio.getAutomovilList() == null) {
            tamanio.setAutomovilList(new ArrayList<Automovil>());
        }
        if (tamanio.getCostoservicioList() == null) {
            tamanio.setCostoservicioList(new ArrayList<Costoservicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Automovil> attachedAutomovilList = new ArrayList<Automovil>();
            for (Automovil automovilListAutomovilToAttach : tamanio.getAutomovilList()) {
                automovilListAutomovilToAttach = em.getReference(automovilListAutomovilToAttach.getClass(), automovilListAutomovilToAttach.getIdAutomovil());
                attachedAutomovilList.add(automovilListAutomovilToAttach);
            }
            tamanio.setAutomovilList(attachedAutomovilList);
            List<Costoservicio> attachedCostoservicioList = new ArrayList<Costoservicio>();
            for (Costoservicio costoservicioListCostoservicioToAttach : tamanio.getCostoservicioList()) {
                costoservicioListCostoservicioToAttach = em.getReference(costoservicioListCostoservicioToAttach.getClass(), costoservicioListCostoservicioToAttach.getIdServicioCosto());
                attachedCostoservicioList.add(costoservicioListCostoservicioToAttach);
            }
            tamanio.setCostoservicioList(attachedCostoservicioList);
            em.persist(tamanio);
            for (Automovil automovilListAutomovil : tamanio.getAutomovilList()) {
                Tamanio oldTamanioidTamanioOfAutomovilListAutomovil = automovilListAutomovil.getTamanioidTamanio();
                automovilListAutomovil.setTamanioidTamanio(tamanio);
                automovilListAutomovil = em.merge(automovilListAutomovil);
                if (oldTamanioidTamanioOfAutomovilListAutomovil != null) {
                    oldTamanioidTamanioOfAutomovilListAutomovil.getAutomovilList().remove(automovilListAutomovil);
                    oldTamanioidTamanioOfAutomovilListAutomovil = em.merge(oldTamanioidTamanioOfAutomovilListAutomovil);
                }
            }
            for (Costoservicio costoservicioListCostoservicio : tamanio.getCostoservicioList()) {
                Tamanio oldTamanioidTamanioOfCostoservicioListCostoservicio = costoservicioListCostoservicio.getTamanioidTamanio();
                costoservicioListCostoservicio.setTamanioidTamanio(tamanio);
                costoservicioListCostoservicio = em.merge(costoservicioListCostoservicio);
                if (oldTamanioidTamanioOfCostoservicioListCostoservicio != null) {
                    oldTamanioidTamanioOfCostoservicioListCostoservicio.getCostoservicioList().remove(costoservicioListCostoservicio);
                    oldTamanioidTamanioOfCostoservicioListCostoservicio = em.merge(oldTamanioidTamanioOfCostoservicioListCostoservicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tamanio tamanio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tamanio persistentTamanio = em.find(Tamanio.class, tamanio.getIdTamanio());
            List<Automovil> automovilListOld = persistentTamanio.getAutomovilList();
            List<Automovil> automovilListNew = tamanio.getAutomovilList();
            List<Costoservicio> costoservicioListOld = persistentTamanio.getCostoservicioList();
            List<Costoservicio> costoservicioListNew = tamanio.getCostoservicioList();
            List<String> illegalOrphanMessages = null;
            for (Automovil automovilListOldAutomovil : automovilListOld) {
                if (!automovilListNew.contains(automovilListOldAutomovil)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Automovil " + automovilListOldAutomovil + " since its tamanioidTamanio field is not nullable.");
                }
            }
            for (Costoservicio costoservicioListOldCostoservicio : costoservicioListOld) {
                if (!costoservicioListNew.contains(costoservicioListOldCostoservicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Costoservicio " + costoservicioListOldCostoservicio + " since its tamanioidTamanio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Automovil> attachedAutomovilListNew = new ArrayList<Automovil>();
            for (Automovil automovilListNewAutomovilToAttach : automovilListNew) {
                automovilListNewAutomovilToAttach = em.getReference(automovilListNewAutomovilToAttach.getClass(), automovilListNewAutomovilToAttach.getIdAutomovil());
                attachedAutomovilListNew.add(automovilListNewAutomovilToAttach);
            }
            automovilListNew = attachedAutomovilListNew;
            tamanio.setAutomovilList(automovilListNew);
            List<Costoservicio> attachedCostoservicioListNew = new ArrayList<Costoservicio>();
            for (Costoservicio costoservicioListNewCostoservicioToAttach : costoservicioListNew) {
                costoservicioListNewCostoservicioToAttach = em.getReference(costoservicioListNewCostoservicioToAttach.getClass(), costoservicioListNewCostoservicioToAttach.getIdServicioCosto());
                attachedCostoservicioListNew.add(costoservicioListNewCostoservicioToAttach);
            }
            costoservicioListNew = attachedCostoservicioListNew;
            tamanio.setCostoservicioList(costoservicioListNew);
            tamanio = em.merge(tamanio);
            for (Automovil automovilListNewAutomovil : automovilListNew) {
                if (!automovilListOld.contains(automovilListNewAutomovil)) {
                    Tamanio oldTamanioidTamanioOfAutomovilListNewAutomovil = automovilListNewAutomovil.getTamanioidTamanio();
                    automovilListNewAutomovil.setTamanioidTamanio(tamanio);
                    automovilListNewAutomovil = em.merge(automovilListNewAutomovil);
                    if (oldTamanioidTamanioOfAutomovilListNewAutomovil != null && !oldTamanioidTamanioOfAutomovilListNewAutomovil.equals(tamanio)) {
                        oldTamanioidTamanioOfAutomovilListNewAutomovil.getAutomovilList().remove(automovilListNewAutomovil);
                        oldTamanioidTamanioOfAutomovilListNewAutomovil = em.merge(oldTamanioidTamanioOfAutomovilListNewAutomovil);
                    }
                }
            }
            for (Costoservicio costoservicioListNewCostoservicio : costoservicioListNew) {
                if (!costoservicioListOld.contains(costoservicioListNewCostoservicio)) {
                    Tamanio oldTamanioidTamanioOfCostoservicioListNewCostoservicio = costoservicioListNewCostoservicio.getTamanioidTamanio();
                    costoservicioListNewCostoservicio.setTamanioidTamanio(tamanio);
                    costoservicioListNewCostoservicio = em.merge(costoservicioListNewCostoservicio);
                    if (oldTamanioidTamanioOfCostoservicioListNewCostoservicio != null && !oldTamanioidTamanioOfCostoservicioListNewCostoservicio.equals(tamanio)) {
                        oldTamanioidTamanioOfCostoservicioListNewCostoservicio.getCostoservicioList().remove(costoservicioListNewCostoservicio);
                        oldTamanioidTamanioOfCostoservicioListNewCostoservicio = em.merge(oldTamanioidTamanioOfCostoservicioListNewCostoservicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tamanio.getIdTamanio();
                if (findTamanio(id) == null) {
                    throw new NonexistentEntityException("The tamanio with id " + id + " no longer exists.");
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
            Tamanio tamanio;
            try {
                tamanio = em.getReference(Tamanio.class, id);
                tamanio.getIdTamanio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tamanio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Automovil> automovilListOrphanCheck = tamanio.getAutomovilList();
            for (Automovil automovilListOrphanCheckAutomovil : automovilListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tamanio (" + tamanio + ") cannot be destroyed since the Automovil " + automovilListOrphanCheckAutomovil + " in its automovilList field has a non-nullable tamanioidTamanio field.");
            }
            List<Costoservicio> costoservicioListOrphanCheck = tamanio.getCostoservicioList();
            for (Costoservicio costoservicioListOrphanCheckCostoservicio : costoservicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tamanio (" + tamanio + ") cannot be destroyed since the Costoservicio " + costoservicioListOrphanCheckCostoservicio + " in its costoservicioList field has a non-nullable tamanioidTamanio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tamanio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tamanio> findTamanioEntities() {
        return findTamanioEntities(true, -1, -1);
    }

    public List<Tamanio> findTamanioEntities(int maxResults, int firstResult) {
        return findTamanioEntities(false, maxResults, firstResult);
    }

    private List<Tamanio> findTamanioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tamanio.class));
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

    public Tamanio findTamanio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tamanio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTamanioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tamanio> rt = cq.from(Tamanio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
