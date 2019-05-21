package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.pojo.Locacao;
import java.sql.SQLException;
import java.util.List;

public class LocacaoBean {
    
    private Locacao locacao;

    public String lista() {
        return "livro/index.xhtml";
    }

    public String cadastra() {
        locacao = new Locacao();
        return "form.xhtml";
    }

    public String edita(Long id) {
        LocacaoDAO dao = new LocacaoDAO();
        locacao = dao.get(id);
        return "form.xhtml";
    }

    public String salva() {
        LocacaoDAO dao = new LocacaoDAO();
        if (locacao.getId() == null) {
            dao.save(locacao);
        } else {
            dao.update(locacao);
        }
        return "index.xhtml";
    }

    public String delete(Locacao locacao) {
        LocacaoDAO dao = new LocacaoDAO();
        dao.delete(locacao);
        return "index.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Locacao> getLocacoes() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        return dao.getAll();
    }

    public Locacao getLocacao() {
        return locacao;
    }
    
}
