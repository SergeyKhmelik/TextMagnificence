package config;

import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Koloturka
 * @version 04.09.2015 14:28
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(new Md5PasswordEncoder())
		;
	}

	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.authenticationEntryPoint(restAuthenticationEntryPoint)
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.rememberMe()
					.tokenValiditySeconds(2419200)
					.key("textMagnificenceKey")
			.and()
				.authorizeRequests()
					.antMatchers("/stories/**").authenticated()
					.antMatchers(HttpMethod.POST, "/stories/**").hasRole(Role.ADMIN.toValue())
					.antMatchers(HttpMethod.PUT, "/stories/**").hasRole(Role.ADMIN.toValue())
					.antMatchers(HttpMethod.DELETE, "stories/**").hasRole(Role.ADMIN.toValue())
					.anyRequest().permitAll()
			.and()
				.logout()
					.logoutSuccessUrl("/")
					.logoutUrl("/logout")
			.and()
				.addFilterBefore(new AuthenticationTokenProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)

//			.and()
//				.csrf()
//					.disable()
		;
	}
	*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.formLogin()
						.loginProcessingUrl("/login")
//			.and()
//				.rememberMe()
//				.tokenValiditySeconds(2419200)
//				.key("textMagnificenceKey")
			.and()
				.authorizeRequests()

				.antMatchers("/stories/**").authenticated()

				.antMatchers(HttpMethod.POST, "**/nextPage").hasRole(Role.USER.toValue())

				.antMatchers(HttpMethod.GET, "**/pages").hasRole(Role.ADMIN.toValue())

				.antMatchers(HttpMethod.GET, "**/screens").hasRole(Role.ADMIN.toValue())
				.antMatchers(HttpMethod.GET, "**/screens/**").hasRole(Role.ADMIN.toValue())


				.antMatchers(HttpMethod.POST, "/stories/**").hasRole(Role.ADMIN.toValue())
		 		.antMatchers(HttpMethod.PUT, "/stories/**").hasRole(Role.ADMIN.toValue())
				.antMatchers(HttpMethod.DELETE, "/stories/**").hasRole(Role.ADMIN.toValue())

				.antMatchers(HttpMethod.GET, "/resources/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api-docs").permitAll()

			.and()
				.logout()
					.logoutSuccessUrl("/stories")
					.logoutUrl("/logout")
			.and()
				.csrf()
					.disable()
		;
	}
}