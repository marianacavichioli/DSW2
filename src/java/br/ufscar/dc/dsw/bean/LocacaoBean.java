package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.pojo.Locacao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;

@ManagedBean
@SessionScoped
public class LocacaoBean implements Serializable {

    private Locacao locacao;

    public String lista() {
        return "locacao/lista.xhtml";
    }
      
    public String listaBusca() {
        return "lista.xhtml";
    }
    
    public String formulario() {
        return "formulario.xhtml";
    }
    
    public String formularioExterno() {
        return "/locacao/formulario.xhtml";
    }
    
    public String listaExterno() {
        return "/locacao/lista.xhtml";
    }

    public String cadastra() {
        locacao = new Locacao();
        return "locacao/formulario.xhtml";
    }

    public String edita(Long id) {
        LocacaoDAO dao = new LocacaoDAO();
        locacao = dao.get(id);
        return "formulario.xhtml";
    }

    public String salva() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        List<Locacao> listaLocacoes = getLocacoes();
        boolean locar = true;

        if (listaLocacoes.size() != 0) {
            for (int i = 0; i < listaLocacoes.size(); i++) {
                if (listaLocacoes.get(i).getCpf_cliente().equals(locacao.getCpf_cliente())
                        && listaLocacoes.get(i).getDia().equals(locacao.getDia())) {

                    int hora_locacao = Integer.parseInt(listaLocacoes.get(i).getHora().substring(0, 2));
                    int hora_locacao_atual = Integer.parseInt(locacao.getHora().substring(0, 2));

                    if (hora_locacao_atual == hora_locacao) {
                        locar = false;
                    }
                } else if (listaLocacoes.get(i).getCnpj_locadora().equals(locacao.getCnpj_locadora())
                        && listaLocacoes.get(i).getDia().equals(locacao.getDia())) {

                    int hora_locacao = Integer.parseInt(listaLocacoes.get(i).getHora());
                    int hora_locacao_atual = Integer.parseInt(locacao.getHora());
                    if (hora_locacao_atual == hora_locacao) {
                        locar = false;
                    }
                }
            }
        }

        if (locar == true) {
            if (locacao.getId() == null) {
                dao.save(locacao);
            } else {
                dao.update(locacao);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não é possível cadastrar uma locação neste horário", "Erro de validação", JOptionPane.ERROR_MESSAGE);
        }

        return "lista.xhtml";
    }

    public String delete(Locacao locacao) {
        LocacaoDAO dao = new LocacaoDAO();
        dao.delete(locacao);
        return "locacao/lista.xhtml";
    }

    public String volta() {
        return "/lista.xhtml?faces-redirect=true";
    }

    public List<Locacao> getLocacoes() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        return dao.getAll();
    }

    public Locacao getLocacao() {
        return locacao;
    }

}
