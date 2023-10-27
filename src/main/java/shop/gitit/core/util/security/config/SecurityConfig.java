package shop.gitit.core.util.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import shop.gitit.core.util.jwt.JwtTokenProvider;
import shop.gitit.core.util.jwt.filter.JwtAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final String USER = "user";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf()
                    .disable()
                    .formLogin()
                    .disable() // 시큐리티의 기본 로그인폼 비활성화
                    .httpBasic()
                    .disable(); // JWT토큰을 사용하여 http bearer를 사용할 것이므로 비활성화;
            http.exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler);
            http.apply(new MyCustomDsl())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/v1/members/login/**")
                    .permitAll()
                    .antMatchers("/v1/**")
                    .authenticated();
            return http.build();
        } catch (Exception e) {
            log.info("security config 에러 발생", e);
            throw new RuntimeException(e);
        }
    }

    private class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            http.addFilter(corsFilter)
                    .addFilterBefore(
                            new JwtAuthenticationFilter(new JwtTokenProvider()),
                            UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
