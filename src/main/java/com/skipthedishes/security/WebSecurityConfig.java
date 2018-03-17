package com.skipthedishes.security;

import static com.skipthedishes.security.SecurityConstants.SIGN_UP_URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JWTBuilder jwtBuilder;

    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, JWTBuilder jwtBuilder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtBuilder = jwtBuilder;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JWTAuthenticationFilter authFilter = new JWTAuthenticationFilter(authenticationManager(), jwtBuilder);
		authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/customers/auth", "POST"));

		http.cors().and().csrf().disable()
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
			.antMatchers("/v2/api-docs/**").permitAll()
		    .antMatchers("/swagger.json").permitAll()
		    .antMatchers("/swagger-ui.html").permitAll()
		    .antMatchers("/swagger-resources/**").permitAll()
		    .antMatchers("/webjars/**").permitAll()
		    .antMatchers("/products/**").permitAll()
		    .antMatchers("/store/**").permitAll()
		    .antMatchers("/cousines/**").permitAll()
			.anyRequest().authenticated().and()
			.addFilter(authFilter)
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtBuilder))
			
			// this disables session creation on Spring Security
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
