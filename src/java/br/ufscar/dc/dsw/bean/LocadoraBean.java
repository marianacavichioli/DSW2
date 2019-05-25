package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Locadora;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LocadoraBean implements Serializable{
    
    private Locadora locadora;

    public String lista() {
        return "locadora/lista.xhtml";
    }

    public String cadastra() {
        locadora = new Locadora();
        return "locadora/formulario.xhtml";
    }

    public String edita(Long id) {
        LocadoraDAO dao = new LocadoraDAO();
        locadora = dao.get(id);
        return "locadora/formulario.xhtml";
    }

    public String salva() {
        LocadoraDAO dao = new LocadoraDAO();
        if (locadora.getId() == null) {
            dao.save(locadora);
        } else {
            dao.update(locadora);
        }
        return "lista.xhtml";
    }

    public String delete(Locadora locadora) {
        LocadoraDAO dao = new LocadoraDAO();
        dao.delete(locadora);
        return "lista.xhtml";
    }

    public String volta() {
        return "/lista.xhtml?faces-redirect=true";
    }

    public List<Locadora> getLocadoras() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        return dao.getAll();
    }
    
    public List<String> getCnpjsLocadoras() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        List<Locadora> locadora = dao.getAll();
        ArrayList<String> cnpjsLocadoras = new ArrayList();
                
        for(int i=0; i<locadora.size(); i++){
            cnpjsLocadoras.add(i, locadora.get(i).getCnpj());
        }
                
        return cnpjsLocadoras;
    }
    
    public List<String> getCidades() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        List<Locadora> locadora = dao.getAll();
        ArrayList<String> cidades = new ArrayList();
                
        for(int i=0; i<locadora.size(); i++){
            cidades.add(i, locadora.get(i).getCidade());
        }
                
        return cidades;
    }
    
    public String pesquisar(String cidade) throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        List<Locadora> locadora = dao.getAll();
        ArrayList<Locadora> locadoras = new ArrayList();
        
        for(int i=0; i<locadora.size(); i++){
            if(locadora.get(i).getCidade().equals(cidade)){
                locadoras.add(locadora.get(i));
            }
        }
        
        return "lista.xhtml";
    }

    public Locadora getLocadora() {
        return locadora;
    }
}
