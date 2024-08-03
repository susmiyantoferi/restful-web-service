package com.restful.webservice.security;

import com.restful.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.security.sasl.AuthenticationException;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableMethodSecurity
@Configuration
public class WebSecurity {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractAuthenticationFilterConfigurer::disable).securityMatcher("/")
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .anyRequest().authenticated()

                );
        return http.build();
    }

//    @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       http.csrf(AbstractHttpConfigurer::disable)
//               .authorizeHttpRequests(authz -> {
//                    authz.requestMatchers(HttpMethod.POST, "/users").permitAll()
//                            .anyRequest().authenticated();
//               }) .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
//       return http.build();
//   }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(HttpMethod.POST, "/users");
//    }


//    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userService = userService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

//    public void configurer(AuthenticationManagerBuilder builder){
//        builder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
//    }
}
