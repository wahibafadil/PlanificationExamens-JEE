package com.teste.monteste.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.teste.monteste.Services.Imp.CustomAuthentifService;

@Configuration // Car cette classe contient des beans (annotées par @bean) qui seront crée
				// automatiquement par Spring
@EnableWebSecurity // Car c'est notre classe de gestion de sécurité donc on active Spring Security
public class AppSecConfig { // Il faut hériter de WebSecurityConfigurerAdapter

	// Logger
	Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

	/**
	 * 
	 * Nous avons choisi d'implémenter une gestion personnalisée de
	 * l'authentification Ainsi, nous devons indiquer à Spring Security quelle est
	 * notre gestionnaire d'authentification Ce gestionnaire est
	 * CustomAuthentificationService
	 * 
	 */
	@Autowired // injection du gestionnaire CustomAuthentificationService
	private CustomAuthentifService userService;

	
	
	@Bean
	public AuthenticationSuccessHandler uthenticatifSuccessHandler() {
		return new RedirectionAfterAuthentifSuccessHandler();
	}

	public AppSecConfig() {

		LOGGER.debug("AppSecurityConfig Initialisé");
	}

	// Configurer DaoAuthenticationProvider (Vous pouvez laisser cette configuration
	// telle quelle)
	@Bean
	public DaoAuthenticationProvider uthProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwdEncoder());
		return authProvider;
	}

	// Indiquer à Spring Security votre Gestionnaire d'authentification
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);

	}

	@Bean
	public AuthenticationFailureHandler uthenticationFailureHandler() {
		return new CustomAuthentifFailureHandler();
	}

	// Configuration de l'algorithme de hashage des mots de passe
	@Bean
	public PasswordEncoder passwdEncoder() {
		// Par défaut on utilise bcrypt
		// TODO : Vous pouvez changer l'algorithme si vous voulez
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean // nécessaire car c'est Spring qui créer automatiquement cette classe de type
	// MySimpleUrlAuthenticationSuccessHandler
	public AuthenticationSuccessHandler redirectionAfterAuthentifSuccessHandler() {
		return new RedirectionAfterAuthentifSuccessHandler();
	}



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// TODO : Configurer la securité de votre application

		http
		//.csrf(csrf -> csrf.disable())  // Disable CSRF
		.authorizeHttpRequests(
				authz -> authz.requestMatchers("/student/")
				.hasRole("STUDENT") //les mappings /student/** sont accessibles uniquementpour ROLE STUDENT  
				.requestMatchers("/cadreadmin/")
				.hasRole("CADRE_ADMIN") 
				.requestMatchers("/prof/")
				.hasRole("PROF")
				.requestMatchers("/admin/")
				.hasRole("ADMIN")
				.anyRequest().permitAll()

		).formLogin(form -> form.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.failureHandler(uthenticationFailureHandler())
				.successHandler(uthenticatifSuccessHandler())
				.permitAll()

		).logout(logout -> logout.logoutUrl("/logout") // Endpoint de déconnexion
//				.deleteCookies("JSESSIONID") // Supprime le cookie JSESSIONID lors de la déconnexion
				.permitAll())
		.exceptionHandling(exception -> exception.accessDeniedPage("/access-denied") 
		);
		return http.build();
	}

}
