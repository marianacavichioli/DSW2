package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Papel;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable{
    
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    private Cliente cliente;

    public String lista() {
        return "cliente/lista.xhtml";
    }
    
    public String cadastra() {
        cliente = new Cliente();
        return "cliente/formulario.xhtml";
    }

    public String edita(Long id) {
        ClienteDAO dao = new ClienteDAO();
        cliente = dao.get(id);
        return "formulario.xhtml";
    }

    public String salva() {
        ClienteDAO dao = new ClienteDAO();
        PapelDAO papelDAO = new PapelDAO();
        
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        cliente.setAtivo(1);
        
        Papel p1 = new Papel();
        p1.setNome("ROLE_CLIENTE");
        papelDAO.save(p1);
        
        if (cliente.getId() == null) {
            dao.save(cliente);
            
            cliente.getPapel().add(p1);
            dao.update(cliente);
        } else {
            dao.update(cliente);
        }
        return "lista.xhtml";
    }

    public String delete(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        dao.delete(cliente);
        return "lista.xhtml";
    }

    public String volta() {
        return "/lista.xhtml?faces-redirect=true";
    }

    public List<Cliente> getClientes() throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        return dao.getAll();
    }
    
    public List<String> getCpfsClientes() throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.getAll();
        ArrayList<String> cpfsClientes = new ArrayList();
                
        for(int i=0; i<clientes.size(); i++){
            cpfsClientes.add(i, clientes.get(i).getCpf());
        }
                
        return cpfsClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }   
}
