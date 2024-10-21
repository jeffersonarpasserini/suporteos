package com.curso.config;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {"/h2-console/**"};
    @Autowired
    private Environment env; //pega o ambiente que o app esta sendo executado

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            // Desabilita o frameOptions para permitir o H2 Console
            http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        }

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Habilita o suporte ao CORS
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Def. política de sessão como STATELESS
            .authorizeHttpRequests(authorize ->
                    authorize.requestMatchers(PUBLIC_URLS).permitAll() // Permite acesso às URLs públicas
                             .anyRequest().authenticated()) // Exige autenticação para qualquer outra requisição
            .csrf(csrf -> csrf.ignoringRequestMatchers(PUBLIC_URLS)) //desabilita CSRF p/ o h2-console
            .formLogin(withDefaults()); // Configura o login padrão
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Define as origens permitidas
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos HTTP permitidos
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Cabeçalhos permitidos
        configuration.setAllowCredentials(true); // Permitir credenciais (cookies, autenticação básica)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica as configurações para todas as rotas
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
