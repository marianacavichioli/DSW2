package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.pojo.Locadora;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

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
        return "locadora/lista.xhtml";
    }

    public String delete(Locadora locadora) {
        LocadoraDAO dao = new LocadoraDAO();
        dao.delete(locadora);
        return "locadora/lista.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Locadora> getLocadoras() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        return dao.getAll();
    }

    public Locadora getLocadora() {
        return locadora;
    }
}