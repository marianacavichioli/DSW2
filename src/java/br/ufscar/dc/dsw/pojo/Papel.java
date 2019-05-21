package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable(value = false)
public class Papel implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nome;
    
    public Papel(Long id, String email, String nome) {
        this.id = id;
        this.email = email;
        this.nome = nome;
    }
    
    public Papel(String email, String nome) {
        this.email = email;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
