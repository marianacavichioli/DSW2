/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Locacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public List<Locacao> getListaCpf(String cpf_cnpj, String usuario) {
        
        EntityManager em = this.getEntityManager();
        Query q;
        if(usuario == "cliente"){
            q = em.createQuery("SELECT l FROM Locacao l WHERE l.cpf_cliente = '" + cpf_cnpj + "'", Locacao.class);
            System.out.println("entrei cliente " + cpf_cnpj);
        }else{
            System.out.println("entrei locadora");
            q = em.createQuery("SELECT l FROM Locacao l WHERE l.cnpj_locadora = '" + cpf_cnpj + "'", Locacao.class);
        }
        
        List<Locacao> lista_locacao = q.getResultList();
        em.close();
        
        return lista_locacao;
    }
    
}

