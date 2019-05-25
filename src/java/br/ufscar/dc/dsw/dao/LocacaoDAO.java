/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Locacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class LocacaoDAO extends GenericDAO<Locacao>{
    
    @Override
    public void save(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(locacao);
        tx.commit();
        em.close();
    }

    public List<Locacao> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locacao l", Locacao.class);
        List<Locacao> locacao = q.getResultList();
        em.close();
        return locacao;
    }

    public void delete(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        locacao = em.getReference(Locacao.class, locacao.getId());
        tx.begin();
        em.remove(locacao);
        tx.commit();
    }

    public void update(Locacao locacao) {         
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(locacao);
        tx.commit();
        em.close();
    }

    public Locacao get(Long id) {
        EntityManager em = this.getEntityManager();
        Locacao locacao = em.find(Locacao.class, id);
        em.close();
        return locacao;
    }
    
}

