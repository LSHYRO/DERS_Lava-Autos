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
import EntityClasses.Tipovehiculo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class TipovehiculoJpaController implements Serializable {

    public TipovehiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipovehiculo tipovehiculo) {
        if (tipovehiculo.getAutomovilList() == null) {
            tipovehiculo.setAutomovilList(new ArrayList<Automovil>());
        }
        if (tipovehiculo.getCostoservicioList() == null) {
            tipovehiculo.setCostoservicioList(new ArrayList<Costoservicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Automovil> attachedAutomovilList = new ArrayList<Automovil>();
            for (Automovil automovilListAutomovilToAttach : tipovehiculo.getAutomovilList()) {
                automovilListAutomovilToAttach = em.getReference(automovilListAutomovilToAttach.getClass(), automovilListAutomovilToAttach.getIdAutomovil());
                attachedAutomovilList.add(automovilListAutomovilToAttach);
            }
            tipovehiculo.setAutomovilList(attachedAutomovilList);
            List<Costoservicio> attachedCostoservicioList = new ArrayList<Costoservicio>();
            for (Costoservicio costoservicioListCostoservicioToAttach : tipovehiculo.getCostoservicioList()) {
                costoservicioListCostoservicioToAttach = em.getReference(costoservicioListCostoservicioToAttach.getClass(), costoservicioListCostoservicioToAttach.getIdServicioCosto());
                attachedCostoservicioList.add(costoservicioListCostoservicioToAttach);
            }
            tipovehiculo.setCostoservicioList(attachedCostoservicioList);
            em.persist(tipovehiculo);
            for (Automovil automovilListAutomovil : tipovehiculo.getAutomovilList()) {
                Tipovehiculo oldTipoVehiculoidTipoVehiculoOfAutomovilListAutomovil = automovilListAutomovil.getTipoVehiculoidTipoVehiculo();
                automovilListAutomovil.setTipoVehiculoidTipoVehiculo(tipovehiculo);
                automovilListAutomovil = em.merge(automovilListAutomovil);
                if (oldTipoVehiculoidTipoVehiculoOfAutomovilListAutomovil != null) {
                    oldTipoVehiculoidTipoVehiculoOfAutomovilListAutomovil.getAutomovilList().remove(automovilListAutomovil);
                    oldTipoVehiculoidTipoVehiculoOfAutomovilListAutomovil = em.merge(oldTipoVehiculoidTipoVehiculoOfAutomovilListAutomovil);
                }
            }
            for (Costoservicio costoservicioListCostoservicio : tipovehiculo.getCostoservicioList()) {
                Tipovehiculo oldTipoVehiculoidTipoVehiculoOfCostoservicioListCostoservicio = costoservicioListCostoservicio.getTipoVehiculoidTipoVehiculo();
                costoservicioListCostoservicio.setTipoVehiculoidTipoVehiculo(tipovehiculo);
                costoservicioListCostoservicio = em.merge(costoservicioListCostoservicio);
                if (oldTipoVehiculoidTipoVehiculoOfCostoservicioListCostoservicio != null) {
                    oldTipoVehiculoidTipoVehiculoOfCostoservicioListCostoservicio.getCostoservicioList().remove(costoservicioListCostoservicio);
                    oldTipoVehiculoidTipoVehiculoOfCostoservicioListCostoservicio = em.merge(oldTipoVehiculoidTipoVehiculoOfCostoservicioListCostoservicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipovehiculo tipovehiculo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipovehiculo persistentTipovehiculo = em.find(Tipovehiculo.class, tipovehiculo.getIdTipoVehiculo());
            List<Automovil> automovilListOld = persistentTipovehiculo.getAutomovilList();
            List<Automovil> automovilListNew = tipovehiculo.getAutomovilList();
            List<Costoservicio> costoservicioListOld = persistentTipovehiculo.getCostoservicioList();
            List<Costoservicio> costoservicioListNew = tipovehiculo.getCostoservicioList();
            List<String> illegalOrphanMessages = null;
            for (Automovil automovilListOldAutomovil : automovilListOld) {
                if (!automovilListNew.contains(automovilListOldAutomovil)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Automovil " + automovilListOldAutomovil + " since its tipoVehiculoidTipoVehiculo field is not nullable.");
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
            tipovehiculo.setAutomovilList(automovilListNew);
            List<Costoservicio> attachedCostoservicioListNew = new ArrayList<Costoservicio>();
            for (Costoservicio costoservicioListNewCostoservicioToAttach : costoservicioListNew) {
                costoservicioListNewCostoservicioToAttach = em.getReference(costoservicioListNewCostoservicioToAttach.getClass(), costoservicioListNewCostoservicioToAttach.getIdServicioCosto());
                attachedCostoservicioListNew.add(costoservicioListNewCostoservicioToAttach);
            }
            costoservicioListNew = attachedCostoservicioListNew;
            tipovehiculo.setCostoservicioList(costoservicioListNew);
            tipovehiculo = em.merge(tipovehiculo);
            for (Automovil automovilListNewAutomovil : automovilListNew) {
                if (!automovilListOld.contains(automovilListNewAutomovil)) {
                    Tipovehiculo oldTipoVehiculoidTipoVehiculoOfAutomovilListNewAutomovil = automovilListNewAutomovil.getTipoVehiculoidTipoVehiculo();
                    automovilListNewAutomovil.setTipoVehiculoidTipoVehiculo(tipovehiculo);
                    automovilListNewAutomovil = em.merge(automovilListNewAutomovil);
                    if (oldTipoVehiculoidTipoVehiculoOfAutomovilListNewAutomovil != null && !oldTipoVehiculoidTipoVehiculoOfAutomovilListNewAutomovil.equals(tipovehiculo)) {
                        oldTipoVehiculoidTipoVehiculoOfAutomovilListNewAutomovil.getAutomovilList().remove(automovilListNewAutomovil);
                        oldTipoVehiculoidTipoVehiculoOfAutomovilListNewAutomovil = em.merge(oldTipoVehiculoidTipoVehiculoOfAutomovilListNewAutomovil);
                    }
                }
            }
            for (Costoservicio costoservicioListOldCostoservicio : costoservicioListOld) {
                if (!costoservicioListNew.contains(costoservicioListOldCostoservicio)) {
                    costoservicioListOldCostoservicio.setTipoVehiculoidTipoVehiculo(null);
                    costoservicioListOldCostoservicio = em.merge(costoservicioListOldCostoservicio);
                }
            }
            for (Costoservicio costoservicioListNewCostoservicio : costoservicioListNew) {
                if (!costoservicioListOld.contains(costoservicioListNewCostoservicio)) {
                    Tipovehiculo oldTipoVehiculoidTipoVehiculoOfCostoservicioListNewCostoservicio = costoservicioListNewCostoservicio.getTipoVehiculoidTipoVehiculo();
                    costoservicioListNewCostoservicio.setTipoVehiculoidTipoVehiculo(tipovehiculo);
                    costoservicioListNewCostoservicio = em.merge(costoservicioListNewCostoservicio);
                    if (oldTipoVehiculoidTipoVehiculoOfCostoservicioListNewCostoservicio != null && !oldTipoVehiculoidTipoVehiculoOfCostoservicioListNewCostoservicio.equals(tipovehiculo)) {
                        oldTipoVehiculoidTipoVehiculoOfCostoservicioListNewCostoservicio.getCostoservicioList().remove(costoservicioListNewCostoservicio);
                        oldTipoVehiculoidTipoVehiculoOfCostoservicioListNewCostoservicio = em.merge(oldTipoVehiculoidTipoVehiculoOfCostoservicioListNewCostoservicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipovehiculo.getIdTipoVehiculo();
                if (findTipovehiculo(id) == null) {
                    throw new NonexistentEntityException("The tipovehiculo with id " + id + " no longer exists.");
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
            Tipovehiculo tipovehiculo;
            try {
                tipovehiculo = em.getReference(Tipovehiculo.class, id);
                tipovehiculo.getIdTipoVehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipovehiculo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Automovil> automovilListOrphanCheck = tipovehiculo.getAutomovilList();
            for (Automovil automovilListOrphanCheckAutomovil : automovilListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipovehiculo (" + tipovehiculo + ") cannot be destroyed since the Automovil " + automovilListOrphanCheckAutomovil + " in its automovilList field has a non-nullable tipoVehiculoidTipoVehiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Costoservicio> costoservicioList = tipovehiculo.getCostoservicioList();
            for (Costoservicio costoservicioListCostoservicio : costoservicioList) {
                costoservicioListCostoservicio.setTipoVehiculoidTipoVehiculo(null);
                costoservicioListCostoservicio = em.merge(costoservicioListCostoservicio);
            }
            em.remove(tipovehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipovehiculo> findTipovehiculoEntities() {
        return findTipovehiculoEntities(true, -1, -1);
    }

    public List<Tipovehiculo> findTipovehiculoEntities(int maxResults, int firstResult) {
        return findTipovehiculoEntities(false, maxResults, firstResult);
    }

    private List<Tipovehiculo> findTipovehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipovehiculo.class));
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

    public Tipovehiculo findTipovehiculo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipovehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipovehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipovehiculo> rt = cq.from(Tipovehiculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
