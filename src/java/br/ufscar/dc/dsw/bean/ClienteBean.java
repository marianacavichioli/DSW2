package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Cliente;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable{
    
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
        
        if (cliente.getId() == null) {
            dao.save(cliente);
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

    public Cliente getCliente() {
        return cliente;
    }
    
}
