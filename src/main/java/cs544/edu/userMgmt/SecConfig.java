package cs544.edu.userMgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by eessc on 8/12/2017.
 */
@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/public/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
//        try {
//            auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
//                    .and().withUser("admin").password("password").roles("USER", "ADMIN");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery(
                            "select username,password,enabled from employee where username=?")
                    .authoritiesByUsernameQuery(
                            "select username, role from employee where username=?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
