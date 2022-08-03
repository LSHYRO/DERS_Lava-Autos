/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import EntityClasses.Costoservicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntityClasses.Servicio;
import EntityClasses.Tamanio;
import EntityClasses.Tipovehiculo;
import EntityClasses.Serviciosolicitado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class CostoservicioJpaController implements Serializable {

    public CostoservicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Costoservicio costoservicio) {
        if (costoservicio.getServiciosolicitadoList() == null) {
            costoservicio.setServiciosolicitadoList(new ArrayList<Serviciosolicitado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicioidServicio = costoservicio.getServicioidServicio();
            if (servicioidServicio != null) {
                servicioidServicio = em.getReference(servicioidServicio.getClass(), servicioidServicio.getIdServicio());
                costoservicio.setServicioidServicio(servicioidServicio);
            }
            Tamanio tamanioidTamanio = costoservicio.getTamanioidTamanio();
            if (tamanioidTamanio != null) {
                tamanioidTamanio = em.getReference(tamanioidTamanio.getClass(), tamanioidTamanio.getIdTamanio());
                costoservicio.setTamanioidTamanio(tamanioidTamanio);
            }
            Tipovehiculo tipoVehiculoidTipoVehiculo = costoservicio.getTipoVehiculoidTipoVehiculo();
            if (tipoVehiculoidTipoVehiculo != null) {
                tipoVehiculoidTipoVehiculo = em.getReference(tipoVehiculoidTipoVehiculo.getClass(), tipoVehiculoidTipoVehiculo.getIdTipoVehiculo());
                costoservicio.setTipoVehiculoidTipoVehiculo(tipoVehiculoidTipoVehiculo);
            }
            List<Serviciosolicitado> attachedServiciosolicitadoList = new ArrayList<Serviciosolicitado>();
            for (Serviciosolicitado serviciosolicitadoListServiciosolicitadoToAttach : costoservicio.getServiciosolicitadoList()) {
                serviciosolicitadoListServiciosolicitadoToAttach = em.getReference(serviciosolicitadoListServiciosolicitadoToAttach.getClass(), serviciosolicitadoListServiciosolicitadoToAttach.getIdServicioSolicitado());
                attachedServiciosolicitadoList.add(serviciosolicitadoListServiciosolicitadoToAttach);
            }
            costoservicio.setServiciosolicitadoList(attachedServiciosolicitadoList);
            em.persist(costoservicio);
            if (servicioidServicio != null) {
                servicioidServicio.getCostoservicioList().add(costoservicio);
                servicioidServicio = em.merge(servicioidServicio);
            }
            if (tamanioidTamanio != null) {
                tamanioidTamanio.getCostoservicioList().add(costoservicio);
                tamanioidTamanio = em.merge(tamanioidTamanio);
            }
            if (tipoVehiculoidTipoVehiculo != null) {
                tipoVehiculoidTipoVehiculo.getCostoservicioList().add(costoservicio);
                tipoVehiculoidTipoVehiculo = em.merge(tipoVehiculoidTipoVehiculo);
            }
            for (Serviciosolicitado serviciosolicitadoListServiciosolicitado : costoservicio.getServiciosolicitadoList()) {
                Costoservicio oldCostoServicioidServicioCostoOfServiciosolicitadoListServiciosolicitado = serviciosolicitadoListServiciosolicitado.getCostoServicioidServicioCosto();
                serviciosolicitadoListServiciosolicitado.setCostoServicioidServicioCosto(costoservicio);
                serviciosolicitadoListServiciosolicitado = em.merge(serviciosolicitadoListServiciosolicitado);
                if (oldCostoServicioidServicioCostoOfServiciosolicitadoListServiciosolicitado != null) {
                    oldCostoServicioidServicioCostoOfServiciosolicitadoListServiciosolicitado.getServiciosolicitadoList().remove(serviciosolicitadoListServiciosolicitado);
                    oldCostoServicioidServicioCostoOfServiciosolicitadoListServiciosolicitado = em.merge(oldCostoServicioidServicioCostoOfServiciosolicitadoListServiciosolicitado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Costoservicio costoservicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Costoservicio persistentCostoservicio = em.find(Costoservicio.class, costoservicio.getIdServicioCosto());
            Servicio servicioidServicioOld = persistentCostoservicio.getServicioidServicio();
            Servicio servicioidServicioNew = costoservicio.getServicioidServicio();
            Tamanio tamanioidTamanioOld = persistentCostoservicio.getTamanioidTamanio();
            Tamanio tamanioidTamanioNew = costoservicio.getTamanioidTamanio();
            Tipovehiculo tipoVehiculoidTipoVehiculoOld = persistentCostoservicio.getTipoVehiculoidTipoVehiculo();
            Tipovehiculo tipoVehiculoidTipoVehiculoNew = costoservicio.getTipoVehiculoidTipoVehiculo();
            List<Serviciosolicitado> serviciosolicitadoListOld = persistentCostoservicio.getServiciosolicitadoList();
            List<Serviciosolicitado> serviciosolicitadoListNew = costoservicio.getServiciosolicitadoList();
            List<String> illegalOrphanMessages = null;
            for (Serviciosolicitado serviciosolicitadoListOldServiciosolicitado : serviciosolicitadoListOld) {
                if (!serviciosolicitadoListNew.contains(serviciosolicitadoListOldServiciosolicitado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Serviciosolicitado " + serviciosolicitadoListOldServiciosolicitado + " since its costoServicioidServicioCosto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (servicioidServicioNew != null) {
                servicioidServicioNew = em.getReference(servicioidServicioNew.getClass(), servicioidServicioNew.getIdServicio());
                costoservicio.setServicioidServicio(servicioidServicioNew);
            }
            if (tamanioidTamanioNew != null) {
                tamanioidTamanioNew = em.getReference(tamanioidTamanioNew.getClass(), tamanioidTamanioNew.getIdTamanio());
                costoservicio.setTamanioidTamanio(tamanioidTamanioNew);
            }
            if (tipoVehiculoidTipoVehiculoNew != null) {
                tipoVehiculoidTipoVehiculoNew = em.getReference(tipoVehiculoidTipoVehiculoNew.getClass(), tipoVehiculoidTipoVehiculoNew.getIdTipoVehiculo());
                costoservicio.setTipoVehiculoidTipoVehiculo(tipoVehiculoidTipoVehiculoNew);
            }
            List<Serviciosolicitado> attachedServiciosolicitadoListNew = new ArrayList<Serviciosolicitado>();
            for (Serviciosolicitado serviciosolicitadoListNewServiciosolicitadoToAttach : serviciosolicitadoListNew) {
                serviciosolicitadoListNewServiciosolicitadoToAttach = em.getReference(serviciosolicitadoListNewServiciosolicitadoToAttach.getClass(), serviciosolicitadoListNewServiciosolicitadoToAttach.getIdServicioSolicitado());
                attachedServiciosolicitadoListNew.add(serviciosolicitadoListNewServiciosolicitadoToAttach);
            }
            serviciosolicitadoListNew = attachedServiciosolicitadoListNew;
            costoservicio.setServiciosolicitadoList(serviciosolicitadoListNew);
            costoservicio = em.merge(costoservicio);
            if (servicioidServicioOld != null && !servicioidServicioOld.equals(servicioidServicioNew)) {
                servicioidServicioOld.getCostoservicioList().remove(costoservicio);
                servicioidServicioOld = em.merge(servicioidServicioOld);
            }
            if (servicioidServicioNew != null && !servicioidServicioNew.equals(servicioidServicioOld)) {
                servicioidServicioNew.getCostoservicioList().add(costoservicio);
                servicioidServicioNew = em.merge(servicioidServicioNew);
            }
            if (tamanioidTamanioOld != null && !tamanioidTamanioOld.equals(tamanioidTamanioNew)) {
                tamanioidTamanioOld.getCostoservicioList().remove(costoservicio);
                tamanioidTamanioOld = em.merge(tamanioidTamanioOld);
            }
            if (tamanioidTamanioNew != null && !tamanioidTamanioNew.equals(tamanioidTamanioOld)) {
                tamanioidTamanioNew.getCostoservicioList().add(costoservicio);
                tamanioidTamanioNew = em.merge(tamanioidTamanioNew);
            }
            if (tipoVehiculoidTipoVehiculoOld != null && !tipoVehiculoidTipoVehiculoOld.equals(tipoVehiculoidTipoVehiculoNew)) {
                tipoVehiculoidTipoVehiculoOld.getCostoservicioList().remove(costoservicio);
                tipoVehiculoidTipoVehiculoOld = em.merge(tipoVehiculoidTipoVehiculoOld);
            }
            if (tipoVehiculoidTipoVehiculoNew != null && !tipoVehiculoidTipoVehiculoNew.equals(tipoVehiculoidTipoVehiculoOld)) {
                tipoVehiculoidTipoVehiculoNew.getCostoservicioList().add(costoservicio);
                tipoVehiculoidTipoVehiculoNew = em.merge(tipoVehiculoidTipoVehiculoNew);
            }
            for (Serviciosolicitado serviciosolicitadoListNewServiciosolicitado : serviciosolicitadoListNew) {
                if (!serviciosolicitadoListOld.contains(serviciosolicitadoListNewServiciosolicitado)) {
                    Costoservicio oldCostoServicioidServicioCostoOfServiciosolicitadoListNewServiciosolicitado = serviciosolicitadoListNewServiciosolicitado.getCostoServicioidServicioCosto();
                    serviciosolicitadoListNewServiciosolicitado.setCostoServicioidServicioCosto(costoservicio);
                    serviciosolicitadoListNewServiciosolicitado = em.merge(serviciosolicitadoListNewServiciosolicitado);
                    if (oldCostoServicioidServicioCostoOfServiciosolicitadoListNewServiciosolicitado != null && !oldCostoServicioidServicioCostoOfServiciosolicitadoListNewServiciosolicitado.equals(costoservicio)) {
                        oldCostoServicioidServicioCostoOfServiciosolicitadoListNewServiciosolicitado.getServiciosolicitadoList().remove(serviciosolicitadoListNewServiciosolicitado);
                        oldCostoServicioidServicioCostoOfServiciosolicitadoListNewServiciosolicitado = em.merge(oldCostoServicioidServicioCostoOfServiciosolicitadoListNewServiciosolicitado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = costoservicio.getIdServicioCosto();
                if (findCostoservicio(id) == null) {
                    throw new NonexistentEntityException("The costoservicio with id " + id + " no longer exists.");
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
            Costoservicio costoservicio;
            try {
                costoservicio = em.getReference(Costoservicio.class, id);
                costoservicio.getIdServicioCosto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The costoservicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Serviciosolicitado> serviciosolicitadoListOrphanCheck = costoservicio.getServiciosolicitadoList();
            for (Serviciosolicitado serviciosolicitadoListOrphanCheckServiciosolicitado : serviciosolicitadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Costoservicio (" + costoservicio + ") cannot be destroyed since the Serviciosolicitado " + serviciosolicitadoListOrphanCheckServiciosolicitado + " in its serviciosolicitadoList field has a non-nullable costoServicioidServicioCosto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Servicio servicioidServicio = costoservicio.getServicioidServicio();
            if (servicioidServicio != null) {
                servicioidServicio.getCostoservicioList().remove(costoservicio);
                servicioidServicio = em.merge(servicioidServicio);
            }
            Tamanio tamanioidTamanio = costoservicio.getTamanioidTamanio();
            if (tamanioidTamanio != null) {
                tamanioidTamanio.getCostoservicioList().remove(costoservicio);
                tamanioidTamanio = em.merge(tamanioidTamanio);
            }
            Tipovehiculo tipoVehiculoidTipoVehiculo = costoservicio.getTipoVehiculoidTipoVehiculo();
            if (tipoVehiculoidTipoVehiculo != null) {
                tipoVehiculoidTipoVehiculo.getCostoservicioList().remove(costoservicio);
                tipoVehiculoidTipoVehiculo = em.merge(tipoVehiculoidTipoVehiculo);
            }
            em.remove(costoservicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Costoservicio> findCostoservicioEntities() {
        return findCostoservicioEntities(true, -1, -1);
    }

    public List<Costoservicio> findCostoservicioEntities(int maxResults, int firstResult) {
        return findCostoservicioEntities(false, maxResults, firstResult);
    }

    private List<Costoservicio> findCostoservicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Costoservicio.class));
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

    public Costoservicio findCostoservicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Costoservicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getCostoservicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Costoservicio> rt = cq.from(Costoservicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
