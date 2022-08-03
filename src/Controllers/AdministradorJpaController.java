/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import EntityClasses.Administrador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntityClasses.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class AdministradorJpaController implements Serializable {

    public AdministradorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrador administrador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioidUsuario = administrador.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
                administrador.setUsuarioidUsuario(usuarioidUsuario);
            }
            em.persist(administrador);
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getAdministradorList().add(administrador);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrador administrador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador persistentAdministrador = em.find(Administrador.class, administrador.getIdAdministrador());
            Usuario usuarioidUsuarioOld = persistentAdministrador.getUsuarioidUsuario();
            Usuario usuarioidUsuarioNew = administrador.getUsuarioidUsuario();
            if (usuarioidUsuarioNew != null) {
                usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
                administrador.setUsuarioidUsuario(usuarioidUsuarioNew);
            }
            administrador = em.merge(administrador);
            if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
                usuarioidUsuarioOld.getAdministradorList().remove(administrador);
                usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
            }
            if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
                usuarioidUsuarioNew.getAdministradorList().add(administrador);
                usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administrador.getIdAdministrador();
                if (findAdministrador(id) == null) {
                    throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.");
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
            Administrador administrador;
            try {
                administrador = em.getReference(Administrador.class, id);
                administrador.getIdAdministrador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioidUsuario = administrador.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getAdministradorList().remove(administrador);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.remove(administrador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrador> findAdministradorEntities() {
        return findAdministradorEntities(true, -1, -1);
    }

    public List<Administrador> findAdministradorEntities(int maxResults, int firstResult) {
        return findAdministradorEntities(false, maxResults, firstResult);
    }

    private List<Administrador> findAdministradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrador.class));
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

    public Administrador findAdministrador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrador> rt = cq.from(Administrador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
