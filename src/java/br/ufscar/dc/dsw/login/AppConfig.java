package br.ufscar.dc.dsw.login;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    private static DataSource dataSource;

    public AppConfig() throws ClassNotFoundException {
        dataSource = AppConfig.getDataSource();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {

        String userSql = "SELECT email, senha, ativo FROM Usuario "
                + " WHERE email = ?";
        String roleSql = "SELECT u.email, p.nome FROM Usuario u, Papel p,"
                + " Usuario_Papel up WHERE up.usuario_id = u.id and"
                + " up.papel_id = p.id and u.email = ?";
        
        builder.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(userSql)
                .authoritiesByUsernameQuery(roleSql)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/faces/locadora/**").hasRole("ADMIN")
                .antMatchers("/faces/cliente/**").hasRole("ADMIN")
                .antMatchers("/faces/locacao/**").hasAnyRole("CLIENTE", "LOCADORA")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.csrf().disable();
    }

    public static DataSource getDataSource() throws ClassNotFoundException {

        if (dataSource == null) {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/LocacaoJSF";
            String user = "root";
            String password = "root";
            dataSource = new DriverManagerDataSource(url, user, password);
        }

        return dataSource;
    }
}
