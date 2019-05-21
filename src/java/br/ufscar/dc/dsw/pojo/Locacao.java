
package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable(value = false)
public class Locacao implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String cpf_cliente;
    private String cnpj_locadora;
    private String dia;
    private String hora;
    
    public Locacao(Long id, String cpf_cliente, String cnpj_locadora, String dia, String hora) {
        this.id = id;
        this.cpf_cliente = cpf_cliente;
        this.cnpj_locadora = cnpj_locadora;
        this.dia = dia;
        this.hora = hora;
    } 

    public Locacao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getCnpj_locadora() {
        return cnpj_locadora;
    }

    public void setCnpj_locadora(String cnpj_locadora) {
        this.cnpj_locadora = cnpj_locadora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
