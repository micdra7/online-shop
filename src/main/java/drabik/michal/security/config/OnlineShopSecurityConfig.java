package drabik.michal.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class OnlineShopSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register-form").anonymous()
                .antMatchers("/register").anonymous()
                .antMatchers("/log-in").anonymous()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").authenticated()
                .antMatchers("/pay").authenticated()
                .antMatchers("/").permitAll()
                .antMatchers("/categories").permitAll()
                .antMatchers("/subcategories").permitAll()
                .antMatchers("/product").permitAll()
                .antMatchers("/cart").permitAll()

                .and()
                .formLogin()
                .loginPage("/log-in")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()

                .and()
                .logout()
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")

                .and()
                .csrf();
    }
}
