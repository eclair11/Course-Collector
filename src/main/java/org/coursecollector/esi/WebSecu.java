package org.coursecollector.esi;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.coursecollector.esi.service.UserService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
public class WebSecu extends WebSecurityConfigurerAdapter {

    @Inject
    UserService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable();
        http.headers().frameOptions().disable(); // permet d'afficher les iframe
        http.authorizeRequests()
                .antMatchers("/h2-console/**", "/img", "/img/**", "/js", "/js/**", "/lib", "/lib/**",
                        "/css/template.css", "/test-data", "/login", "/signUp", "/login/**", "/public", "/public/**")
                .permitAll().and().csrf().ignoringAntMatchers("/h2-console/**");
        http.authorizeRequests().antMatchers("/admin", "/admin/**").hasRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();

        http.formLogin().loginPage("/login").successForwardUrl("/")// use a custom login URI

                .and().logout() // NB: CSRF will disallow visiting GET /logout manually
                .logoutSuccessUrl("/login?logout")// our new logout success url, we are not replacing other defaults.
        ;
    }

    @Bean

    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/src/main/resources/templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("esi").password("{noop}esipass").roles("ADMIN");

        auth.userDetailsService(userDetailsService).passwordEncoder(userDetailsService.encoder);

    }
}
