/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private Usuario usuario;

    public String lista() {
        return "livro/index.xhtml";
    }

    public String cadastra() {
        usuario = new Usuario();
        return "form.xhtml";
    }

    public String edita(Long id) {
        UsuarioDAO dao = new UsuarioDAO();
        usuario = dao.get(id);
        return "form.xhtml";
    }

    public String salva() {
        UsuarioDAO dao = new UsuarioDAO();
        if (usuario.getId() == null) {
            dao.save(usuario);
        } else {
            dao.update(usuario);
        }
        return "index.xhtml";
    }

    public String delete(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.delete(usuario);
        return "index.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Usuario> getUsuarios() throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.getAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
