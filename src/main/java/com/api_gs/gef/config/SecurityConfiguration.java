package com.api_gs.gef.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final SecurityFilter securityFilter;
    @Autowired
    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

                 // 🔓 Libera Swagger
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()

                // 🔓 Libera acesso público para listagem de abrigos
                .requestMatchers(HttpMethod.POST, "/abrigos/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/funcionario/**").permitAll()

                // 🔐 Somente ADMINISTRADOR pode acessar essas rotas:
                .requestMatchers(HttpMethod.GET, "/funcionarios/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.GET, "/pacientes/**").hasRole("ADMINISTRADOR")
                // .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMINISTRADOR")
                // .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMINISTRADOR")
                // .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMINISTRADOR")

                // ✅ Qualquer outra requisição exige autenticação
                .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }



    @Bean
    CorsConfigurationSource corsConfig(){
        var config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000/"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
