package cs544.edu.userMgmt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by eessc on 8/12/2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/cust/newCust").permitAll()
                .antMatchers("/emp/changePw").authenticated()
                .antMatchers("/").authenticated()
                .antMatchers("/vehicles/**").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/cust/profile").hasRole("CUSTOMER")
                .antMatchers("/cust/**").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/rental/**").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/emp/**").hasAnyRole("MANAGER","EMPLOYEE")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/",true).permitAll();
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
                            "select username, role from employee where username=?").passwordEncoder(passwordEncoder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
