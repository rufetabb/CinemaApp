package com.example.tmsproject.config;

import com.example.tmsproject.service.CustomerService;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class WebConfiguration {
    private CustomerService userService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    public WebConfiguration(CustomerService userService) {
        this.userService = userService;
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
         DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
         auth.setPasswordEncoder(new BCryptPasswordEncoder());
         auth.setUserDetailsService(userService);
         return auth;
    }

    public void configure(AuthenticationManagerBuilder auth){

        auth.authenticationProvider(authenticationProvider());
    }

     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(
                        "/registration/**",
                        "/css/**",
                        "/img/**",
                        "/login/**",
                        "/static/**",
                        "/js/**"
                      ).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/buy-ticket/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/home").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true);
         http.csrf().disable();
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
         return (web)->web.ignoring().antMatchers("/resources/**","/img/**","/css/**","/js/**");
    }



}
