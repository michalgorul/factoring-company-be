package com.polsl.factoringcompany.security;

import com.polsl.factoringcompany.security.auth.ApplicationUserService;
import com.polsl.factoringcompany.security.jwt.JwtTokenVerifier;
import com.polsl.factoringcompany.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;
import java.util.Arrays;

import static com.polsl.factoringcompany.security.ApplicationUserRole.ADMIN;

/**
 * The type Application security configuration class.
 * <a href="https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.html">Click for more info</a>
 * @author Michal Goral
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * the bean of password encoder
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * the bean of application user service class
     */
    private final ApplicationUserService applicationUserService;

    /**
     * the secret key
     */
    private final SecretKey secretKey;

    /**
     * Configures security properties for application.
     * @param http the http security
     * @throws Exception the exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure()
                .and()
                //remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // enable cors globally
                .cors()
                .and()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
                // configure access rules
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/registration**").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name());
    }

    /**
     * Configures authentication provider
     * @param auth the AuthenticationManagerBuilder instance
     * @throws Exception the exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * Bean of DAO authentication provider.
     *
     * @return the dao authentication provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);

        return provider;
    }

    /**
     * Configures cors filter cors for security of application.
     *
     * @return the cors filter bean
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080",
                "https://factoring-company-frontend.herokuapp.com", "https://factoring-company.herokuapp.com"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
                "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


}
