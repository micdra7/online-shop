package drabik.michal.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class OnlineShopSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT users.username, roles.name FROM" +
                        " users INNER JOIN user_roles ON users.id=user_roles.user_id " +
                        "INNER JOIN roles ON user_roles.role_id=roles.id " +
                        "WHERE users.username=?");
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
                .antMatchers("/review").authenticated()
                .antMatchers("/add-review").authenticated()
                .antMatchers("/").permitAll()
                .antMatchers("/categories").permitAll()
                .antMatchers("/subcategories").permitAll()
                .antMatchers("/product").permitAll()
                .antMatchers("/cart").permitAll()
                .antMatchers("/search").permitAll()
                .antMatchers("/search-results").permitAll()

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
