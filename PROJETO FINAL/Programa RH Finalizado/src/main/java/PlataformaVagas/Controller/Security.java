package PlataformaVagas.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").authorities("ROLE_USER").and()
				.withUser("admin").password("{noop}admin").authorities("ROLE_ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/vagas").permitAll() // Permite acesso à página de vagas para todos os
																	// usuários
				.antMatchers("/vagasUsuario").authenticated() // Requer autenticação para acessar a página de vagas do
																// usuário normal
				.antMatchers("/ListaFunc", "/cadastroFunc").hasAuthority("ROLE_ADMIN")

				.antMatchers("/editar-vaga/**").hasAuthority("ROLE_ADMIN")// Restringe o acesso à página de funcionários
																			// apenas para usuários com a função de
																			// administrador
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll().and().csrf().disable();
	}
}
