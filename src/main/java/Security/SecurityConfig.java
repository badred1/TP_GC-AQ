package Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImp userDetailsService;
	@Autowired
	private BCryptPasswordEncoder Bcrypt;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// auth.inMemoryAuthentication().withUser("anas").password("123").roles("ADMIN");
		auth.userDetailsService(userDetailsService).passwordEncoder(Bcrypt);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// http.formLogin();

		http.authorizeRequests().antMatchers("/artisan/allArtisans", "/client/allClients", "/service/addService",
				"/service/updateService").permitAll();
		http.authorizeRequests()
				.antMatchers("/artisan/delete/**", "/client/delete/**", "/service/delete/**", "/admin/**")
				.permitAll();

		http.authorizeRequests().antMatchers("/client/updateClient", "/artisan/findArtisanByName/**",
				"/artisan/findArtisanByService/**").permitAll();
		http.authorizeRequests().antMatchers("/updateArtisan", "/client/findClientByName/**","client/findClient/**")
				.permitAll();

		http.authorizeRequests().antMatchers("service/findServiceByName/**").permitAll();
		http.authorizeRequests().antMatchers("/login/**").permitAll();
		http.authorizeRequests().antMatchers("/artisan/addArtisan", "/client/addClient", "/service/allServices")
				.permitAll();
		// http.authorizeRequests().anyRequest().authenticated();
		//http.authorizeRequests().anyRequest().permitAll();

		http.addFilter(new JwtAuthentificationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
