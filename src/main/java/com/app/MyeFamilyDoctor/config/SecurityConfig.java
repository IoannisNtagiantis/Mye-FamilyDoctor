/*package com.app.MyeFamilyDoctor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Απενεργοποιήστε το CSRF για απλούστευση σε περιβάλλον δοκιμών
                .authorizeRequests()
                .antMatchers("/login", "/register").permitAll() // Επιτρέψτε την πρόσβαση σε αυτά τα endpoints χωρίς αυθεντικοποίηση
                .anyRequest().authenticated() // Απαιτήστε αυθεντικοποίηση για όλα τα άλλα endpoints
                .and()
                .httpBasic(); // Χρησιμοποιήστε βασική αυθεντικοποίηση
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Χρησιμοποιήστε το BCryptPasswordEncoder για το hashing των κωδικών πρόσβασης
    }

    // Προαιρετικά: Ρυθμίστε μια απλή μνήμη χρήστη για την αυθεντικοποίηση (προσθέστε τους δικούς σας χρήστες)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
    }
}*/
