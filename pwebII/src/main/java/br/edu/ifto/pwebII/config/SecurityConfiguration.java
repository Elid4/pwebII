package br.edu.ifto.pwebII.config;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // classe de configuração
@EnableWebSecurity // indica que serão definidas configurações personalizadas de segurança
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**","/assets/**").permitAll()
                        .requestMatchers("/paciente/form").permitAll()
                        .requestMatchers( "/").permitAll()
                        .requestMatchers("/paciente/list").hasAnyRole("ADMIN","SECRETARIO")
                        .requestMatchers("/medico/**").hasAnyRole("ADMIN", "SECRETARIO")

                        //PACIENTE
                        .requestMatchers(HttpMethod.POST, "/paciente/save").permitAll()

                        // Excluir: qualquer usuário autenticado
                        .requestMatchers(HttpMethod.GET, "/paciente/remove").authenticated()

                        // Listar: somente ADMIN e SECRETARIO
                        .requestMatchers(HttpMethod.GET, "/paciente/list").hasAnyRole("ADMIN", "SECRETARIO")

                        // MÉDICO
                        .requestMatchers(HttpMethod.GET, "/medico/form").hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.POST, "/medico/save").hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.GET, "/medico/remove").hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.GET, "/medico/list").hasAnyRole("ADMIN", "SECRETARIO")


                        .anyRequest().authenticated() // demais requisições exigem usuário autenticado
                )
                .formLogin(form -> form
                        .loginPage("/login") // URL da página de login que criaremos
                        .defaultSuccessUrl("/", true)
                        .permitAll() // a página de login é acessível sem autenticação
                )
                .httpBasic(withDefaults()) // autenticação básica (usuário e senha no cabeçalho HTTP)
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .rememberMe(withDefaults()); // mantém o usuário autenticado após fechar o navegador
        return http.build();
    }


    /**
     * Instancia o codificador BCrypt e deixa seu controle sob responsabilidade do Spring.
     * Sempre que o Spring Security precisar codificar ou comparar uma senha, utilizará este bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

