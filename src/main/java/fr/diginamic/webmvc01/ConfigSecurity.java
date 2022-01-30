package fr.diginamic.webmvc01;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// attention comment desactive spring boot securiter en tapant logout sur la barre adresse localhost:8080:logout on desactive le token
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		
		//site inaxessible pas de login rien
		//http.csrf().disable().authorizeRequests().anyRequest().authenticated();
		
		
//		http.csrf().disable()
//        .formLogin().loginProcessingUrl("/login").and()
//        .logout().logoutUrl("/logout").invalidateHttpSession(true).and()
//        .authorizeRequests()
//        .antMatchers("/login").permitAll()
//        .antMatchers("/logout").permitAll()
//        .anyRequest().authenticated().and().httpBasic();
		/**
		 * autoriasation api rest avec httpbasic() avec leur propre securiter
		 */
		
	}

}
