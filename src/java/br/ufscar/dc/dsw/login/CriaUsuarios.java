package br.ufscar.dc.dsw.login;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Locacao;
import br.ufscar.dc.dsw.pojo.Locadora;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriaUsuarios {

     public static void main(String[] args) throws ClassNotFoundException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        PapelDAO papelDAO = new PapelDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Criando Usuario admin com papel ROLE_ADMIN
     
        Usuario u1 = new Usuario();
        u1.setEmail("admin@admin");
        u1.setSenha(encoder.encode("admin"));
        u1.setAtivo(1);
        usuarioDAO.save(u1);

        Papel p1 = new Papel();
        p1.setNome("ROLE_ADMIN");
        papelDAO.save(p1);

        u1.getPapel().add(p1);
        usuarioDAO.update(u1);
        
        // Criando Usuario admin com papel ROLE_CLIENTE
        
        ClienteDAO cdao = new ClienteDAO();
        Cliente u2 = new Cliente();
        u2.setEmail("cliente@cliente");
        u2.setSenha(encoder.encode("cliente"));
        u2.setCpf("34445638846");
        u2.setAtivo(1);
        cdao.save(u2);

        Papel p2 = new Papel();
        p2.setNome("ROLE_CLIENTE");
        papelDAO.save(p2);

        u2.getPapel().add(p2);
        cdao.update(u2);
        
        // Criando Usuario admin com papel ROLE_LOCADORA
        
        LocadoraDAO ldao = new LocadoraDAO();
        
        Locadora u3 = new Locadora();
        u3.setEmail("locadora@locadora");
        u3.setCnpj("1");
        u3.setSenha(encoder.encode("locadora"));
        u3.setAtivo(1);
        ldao.save(u3);

        Papel p3 = new Papel();
        p3.setNome("ROLE_LOCADORA");
        papelDAO.save(p3);

        u3.getPapel().add(p3);
        ldao.update(u3);
        
        // Criando uma locacao fake
        Locacao l1 = new Locacao();
        l1.setCnpj_locadora("1");
        l1.setCpf_cliente("34445638846");
        LocacaoDAO dao = new LocacaoDAO();
        dao.save(l1);
    }
}
