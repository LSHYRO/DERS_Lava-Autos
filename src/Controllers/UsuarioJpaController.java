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
import EntityClasses.Administrador;
import java.util.ArrayList;
import java.util.List;
import EntityClasses.Lavador;
import EntityClasses.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juan6
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getAdministradorList() == null) {
            usuario.setAdministradorList(new ArrayList<Administrador>());
        }
        if (usuario.getLavadorList() == null) {
            usuario.setLavadorList(new ArrayList<Lavador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Administrador> attachedAdministradorList = new ArrayList<Administrador>();
            for (Administrador administradorListAdministradorToAttach : usuario.getAdministradorList()) {
                administradorListAdministradorToAttach = em.getReference(administradorListAdministradorToAttach.getClass(), administradorListAdministradorToAttach.getIdAdministrador());
                attachedAdministradorList.add(administradorListAdministradorToAttach);
            }
            usuario.setAdministradorList(attachedAdministradorList);
            List<Lavador> attachedLavadorList = new ArrayList<Lavador>();
            for (Lavador lavadorListLavadorToAttach : usuario.getLavadorList()) {
                lavadorListLavadorToAttach = em.getReference(lavadorListLavadorToAttach.getClass(), lavadorListLavadorToAttach.getIdLavador());
                attachedLavadorList.add(lavadorListLavadorToAttach);
            }
            usuario.setLavadorList(attachedLavadorList);
            em.persist(usuario);
            for (Administrador administradorListAdministrador : usuario.getAdministradorList()) {
                Usuario oldUsuarioidUsuarioOfAdministradorListAdministrador = administradorListAdministrador.getUsuarioidUsuario();
                administradorListAdministrador.setUsuarioidUsuario(usuario);
                administradorListAdministrador = em.merge(administradorListAdministrador);
                if (oldUsuarioidUsuarioOfAdministradorListAdministrador != null) {
                    oldUsuarioidUsuarioOfAdministradorListAdministrador.getAdministradorList().remove(administradorListAdministrador);
                    oldUsuarioidUsuarioOfAdministradorListAdministrador = em.merge(oldUsuarioidUsuarioOfAdministradorListAdministrador);
                }
            }
            for (Lavador lavadorListLavador : usuario.getLavadorList()) {
                Usuario oldUsuarioidUsuarioOfLavadorListLavador = lavadorListLavador.getUsuarioidUsuario();
                lavadorListLavador.setUsuarioidUsuario(usuario);
                lavadorListLavador = em.merge(lavadorListLavador);
                if (oldUsuarioidUsuarioOfLavadorListLavador != null) {
                    oldUsuarioidUsuarioOfLavadorListLavador.getLavadorList().remove(lavadorListLavador);
                    oldUsuarioidUsuarioOfLavadorListLavador = em.merge(oldUsuarioidUsuarioOfLavadorListLavador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            List<Administrador> administradorListOld = persistentUsuario.getAdministradorList();
            List<Administrador> administradorListNew = usuario.getAdministradorList();
            List<Lavador> lavadorListOld = persistentUsuario.getLavadorList();
            List<Lavador> lavadorListNew = usuario.getLavadorList();
            List<String> illegalOrphanMessages = null;
            for (Administrador administradorListOldAdministrador : administradorListOld) {
                if (!administradorListNew.contains(administradorListOldAdministrador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Administrador " + administradorListOldAdministrador + " since its usuarioidUsuario field is not nullable.");
                }
            }
            for (Lavador lavadorListOldLavador : lavadorListOld) {
                if (!lavadorListNew.contains(lavadorListOldLavador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lavador " + lavadorListOldLavador + " since its usuarioidUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Administrador> attachedAdministradorListNew = new ArrayList<Administrador>();
            for (Administrador administradorListNewAdministradorToAttach : administradorListNew) {
                administradorListNewAdministradorToAttach = em.getReference(administradorListNewAdministradorToAttach.getClass(), administradorListNewAdministradorToAttach.getIdAdministrador());
                attachedAdministradorListNew.add(administradorListNewAdministradorToAttach);
            }
            administradorListNew = attachedAdministradorListNew;
            usuario.setAdministradorList(administradorListNew);
            List<Lavador> attachedLavadorListNew = new ArrayList<Lavador>();
            for (Lavador lavadorListNewLavadorToAttach : lavadorListNew) {
                lavadorListNewLavadorToAttach = em.getReference(lavadorListNewLavadorToAttach.getClass(), lavadorListNewLavadorToAttach.getIdLavador());
                attachedLavadorListNew.add(lavadorListNewLavadorToAttach);
            }
            lavadorListNew = attachedLavadorListNew;
            usuario.setLavadorList(lavadorListNew);
            usuario = em.merge(usuario);
            for (Administrador administradorListNewAdministrador : administradorListNew) {
                if (!administradorListOld.contains(administradorListNewAdministrador)) {
                    Usuario oldUsuarioidUsuarioOfAdministradorListNewAdministrador = administradorListNewAdministrador.getUsuarioidUsuario();
                    administradorListNewAdministrador.setUsuarioidUsuario(usuario);
                    administradorListNewAdministrador = em.merge(administradorListNewAdministrador);
                    if (oldUsuarioidUsuarioOfAdministradorListNewAdministrador != null && !oldUsuarioidUsuarioOfAdministradorListNewAdministrador.equals(usuario)) {
                        oldUsuarioidUsuarioOfAdministradorListNewAdministrador.getAdministradorList().remove(administradorListNewAdministrador);
                        oldUsuarioidUsuarioOfAdministradorListNewAdministrador = em.merge(oldUsuarioidUsuarioOfAdministradorListNewAdministrador);
                    }
                }
            }
            for (Lavador lavadorListNewLavador : lavadorListNew) {
                if (!lavadorListOld.contains(lavadorListNewLavador)) {
                    Usuario oldUsuarioidUsuarioOfLavadorListNewLavador = lavadorListNewLavador.getUsuarioidUsuario();
                    lavadorListNewLavador.setUsuarioidUsuario(usuario);
                    lavadorListNewLavador = em.merge(lavadorListNewLavador);
                    if (oldUsuarioidUsuarioOfLavadorListNewLavador != null && !oldUsuarioidUsuarioOfLavadorListNewLavador.equals(usuario)) {
                        oldUsuarioidUsuarioOfLavadorListNewLavador.getLavadorList().remove(lavadorListNewLavador);
                        oldUsuarioidUsuarioOfLavadorListNewLavador = em.merge(oldUsuarioidUsuarioOfLavadorListNewLavador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Administrador> administradorListOrphanCheck = usuario.getAdministradorList();
            for (Administrador administradorListOrphanCheckAdministrador : administradorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Administrador " + administradorListOrphanCheckAdministrador + " in its administradorList field has a non-nullable usuarioidUsuario field.");
            }
            List<Lavador> lavadorListOrphanCheck = usuario.getLavadorList();
            for (Lavador lavadorListOrphanCheckLavador : lavadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Lavador " + lavadorListOrphanCheckLavador + " in its lavadorList field has a non-nullable usuarioidUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
