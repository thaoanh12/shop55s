package com.example.shop55_be.security;

//import com.example.shop55_be.jwt.JwtAuthenticationFilter;
import com.example.shop55_be.model.ERole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return new JwtAuthenticationFilter();
//    }

    @Bean
    public AuthenticationManager
    authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll()
                .requestMatchers("/view-login").permitAll()
                .requestMatchers("/authen/login").permitAll()
                .requestMatchers("/403error").permitAll()
                .requestMatchers("/static/**").permitAll()
                .anyRequest().authenticated()
        )
                .formLogin(login -> login.loginPage("/view-login").loginProcessingUrl("/authen/login")
                        .usernameParameter("email").passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            if(authentication.getAuthorities().stream()
                                    .allMatch(role -> role.getAuthority().equals("ROLE_CUSTOMER"))){
                                response.sendRedirect("/");

                                CustomUser customUser = (CustomUser) authentication.getPrincipal();
                                request.getSession().setAttribute("code",customUser.getCode());

                            }else {
                                CustomUser customUser = (CustomUser) authentication.getPrincipal();
                                request.getSession().setAttribute("code",customUser.getCode());
                                response.sendRedirect("/admin/");
                            }
                        })
                )
                .exceptionHandling(exception -> exception.accessDeniedPage("/403error"))
//                .addFilterBefore(jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
        .headers(header -> header.frameOptions().disable())
        ;
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers("/static/**","/qrcodes/**");
    }
}
