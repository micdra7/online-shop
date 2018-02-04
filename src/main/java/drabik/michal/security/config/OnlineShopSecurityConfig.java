package drabik.michal.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class OnlineShopSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("john").password("test").roles("CUSTOMER"))
                .withUser(users.username("mike").password("test").roles("ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").anonymous()
                .antMatchers("/categories/**").anonymous()
                .antMatchers("/register").anonymous()
                .antMatchers("/log-in").anonymous()
                .antMatchers("/cart").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/user/**").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")

                .and()
                .formLogin()
                .loginPage("/log-in")
                .loginProcessingUrl("/authenticate")
                .permitAll()

                .and()
                .logout()
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }
}
