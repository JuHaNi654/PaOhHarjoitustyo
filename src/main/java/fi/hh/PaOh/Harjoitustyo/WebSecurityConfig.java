package fi.hh.PaOh.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import fi.hh.PaOh.Harjoitustyo.userService.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() 
			.authorizeRequests()
				//Ei tarvitse olla sisäänkirjautunut, jotta voi päästä näille sivuille
				// Let users to get site without login
				.antMatchers("/resources/**", "/tracklist", "/tracklist/{trackId}/carlist", "/tracklist/{trackId}/carlist/car-info/{carId}", "/signup", "/saveuser").permitAll()
				//Ei tarvitse olla sisäänkirjautunut, jotta voi käyttää Rest-methodeja
				//To use Rest-service data, no need login
				.antMatchers("/", "/cars", "/cars/{carId}", "/tracks", "/tracks/{trackId}", "/carclass", "/carclass/{carClass}").permitAll()
				// Only admin action privileges in system
				.antMatchers("/tracklist/delete/{trackId}").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				//After success login program redirect to tracklist page
				.defaultSuccessUrl("/tracklist") //Ohjaa tracklist sivustolle onnistuneesta sisänkirjauksesta
				.permitAll()
				.and()
			.logout()
				////After success logout program redirect to tracklist page
				.logoutSuccessUrl("/tracklist") //Ohjaa tracklist sivustolle onnistuneesta uloskirjauksesta
				.permitAll();

	}
	
	//Crypt given new account password
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
