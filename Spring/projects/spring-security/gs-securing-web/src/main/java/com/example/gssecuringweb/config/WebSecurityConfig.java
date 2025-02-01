package com.example.gssecuringweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // to enable Spring Security’s web security support and provide the Spring MVC integration.
public class WebSecurityConfig {

    /**
     * - SecurityFilterChain bean defines which URL Paths should be secured and which should not.
     * - When a user successfully logs in, they are redirected to the previously requested page that required authentication. (Security 기본 동작)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // ensures that only authenticated users can see the secret greeting
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll() // 인증 없이 접근 가능한 경로
                        // 인증되지 않은 사용자는 Security가 /login으로 자동으로 302 리다이렉트 시킨다.
                        // 리다이렉트 시키기 전에, 서버 세션에 JSESSIONID에 매핑되는 SPRING_SECURITY_SAVED_REQUEST에 이전 요청 URL을 담아두고,
                        // 로그인 성공 시 JSESSIONID에 매핑되는 서버 세션에서 SPRING_SECURITY_SAVED_REQUEST를 삭제하고 302로 리다이렉트한다.
                        .anyRequest().authenticated() // 그 외 모든 경로는 인증 필요.
                )
                .formLogin((form) -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 URL 지정
                        .permitAll() // 로그인 페이지는 인증 없이 접근 가능
                )
                // /logout POST 요청 성공 시 기본적으로 /login?logout으로 302 redirect
                .logout((logout) -> logout.permitAll()); // 로그아웃도 인증 없이 접근 가능

        return http.build();
    }

    /**
     * The UserDetailsService bean sets up an in-memory user store with a single user.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pw")
                .roles("USER")
                .build();

        // 메모리에 임시로 사용자 정보를 저장. 실 서비스에서는 절대 사용하면 안 되는 방식.
        return new InMemoryUserDetailsManager(user);
    }
}
