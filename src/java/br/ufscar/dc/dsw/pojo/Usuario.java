
package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable(value = false)
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    private int ativo;
    
 
    public Usuario(Long id, String email, String senha, int ativo) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Usuario(String senha, String email, int ativo){
        this.senha = senha;
        this.email = email;
        this.ativo = ativo;
    }
    
    public Usuario(String email, int ativo){
        this.email = email;
        this.ativo = ativo;
    }

    public Usuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
}

