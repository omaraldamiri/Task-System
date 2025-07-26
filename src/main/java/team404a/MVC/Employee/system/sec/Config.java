package team404a.MVC.Employee.system.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import team404a.MVC.Employee.system.controllers.CustomAuthenticationSuccessHandler;
import team404a.MVC.Employee.system.service.UserService;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager manager=new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username,password,is_active FROM Users WHERE username=?");
        manager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, r.role_name AS authority " +
                        "FROM users u " +
                        "JOIN roles r ON u.username = r.username " +
                        "WHERE u.username = ?"
        );

        return manager;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception{

        http.authorizeHttpRequests(config ->
                    config.requestMatchers("/auth/**").permitAll().
                            requestMatchers("managers/**").hasRole("MANAGER")
                            .requestMatchers("/admins/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
        ).formLogin(
                form -> form.loginPage("/auth/login").permitAll().loginProcessingUrl("/authUser")
                .successHandler(customAuthenticationSuccessHandler))
                .logout(logout -> logout.permitAll())
                .exceptionHandling(exp -> exp.accessDeniedPage("/access-denied"));


        return http.build();
    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService){
//        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userService);
//        auth.setPasswordEncoder(passwordEncoder());
//
//        return auth;
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
