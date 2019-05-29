package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ClienteDAO extends GenericDAO<Cliente>{
    
    @Override
    public void save(Cliente cliente) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(cliente);
        tx.commit();
        em.close();
    }

    public List<Cliente> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select c from Cliente c", Cliente.class);
        List<Cliente> cliente = q.getResultList();
        em.close();
        return cliente;
    }
    
    public void delete(Cliente cliente) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        cliente = em.getReference(Cliente.class, cliente.getId());
        tx.begin();
        em.remove(cliente);
        tx.commit();
    }

    public void update(Cliente cliente) {         
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(cliente);
        tx.commit();
        em.close();
    }

    public Cliente get(Long id) {
        EntityManager em = this.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return cliente;
    }
    
    public String getCPF(Long id){
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("SELECT c FROM Cliente c, Usuario u WHERE c.id = " + id + " and c.id = u.id", Cliente.class);
        Cliente c = (Cliente) q.getSingleResult();
        return c.getCpf();
    }
    
    public Cliente getByEmail(String email) {
        EntityManager em = this.getEntityManager();
        TypedQuery <Cliente> q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = '" + email + "'", Cliente.class);
        Cliente c = q.getSingleResult();
        em.close();
        return c; 
    }
    
}
