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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {

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

        cliente.setSenha(encoder.encode(cliente.getSenha()));
        cliente.setAtivo(1);
        
        Boolean email_existe = isValidEmailAddressRegex(cliente.getEmail());
        if(email_existe){
            System.out.println("email existe");
        }else{
            System.out.println("email nao existe");
        }

        Papel p1 = new PapelDAO().get(2L);

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

        for (int i = 0; i < clientes.size(); i++) {
            cpfsClientes.add(i, clientes.get(i).getCpf());
        }

        return cpfsClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

}
