
package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;

@Entity
@Cacheable(value = false)
public class Cliente extends Usuario implements Serializable{
    
    private Long id;
    private String nome;
    private String data_nascimento;
    private String sexo;
    private String cpf;
    private String telefone;

    public Cliente(Long id, String nome, String cpf, String telefone, String data_nascimento, String sexo){
        super(null, null, -1);
        this.id = id;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    
    public Cliente(Long id, String nome, String cpf, String telefone, String data_nascimento, String sexo, String senha, String email, int ativo) {
        super(senha, email, ativo);
        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Cliente(Long id) {
        super(null, null, -1);
        this.id = id;
    }

    public Cliente(Long id, String nome, String cpf, String telefone, String data_nascimento, String sexo, String email, int ativo) {
        super(email, ativo);
        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Cliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
