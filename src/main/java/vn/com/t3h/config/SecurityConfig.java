package vn.com.t3h.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/cms/**").hasRole("ADMIN") // Chỉ được phép truy cập các url bắt đầu bằng cms như http://localhost:8080/cms/... với quyền ADMIN
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // được phép truy cập các url bắt đầu bằng cms như http://localhost:8080/user/... với quyền ADMIN hoặc USER
                        .requestMatchers("/", "/home-guest", "/login", // config để vào được các page home, login mà không bắt buộc login
                                // các config để cho phép lấy ra html, css từ server mà không cần login
                                "/assets/**", "/fonts/**", "/homeguest_files/**",
                                "/js/**", "/libs/**", "/loginmetlife/**",
                                "/page404/**", "/scss/**", "/tasks/**", "/css/**", "/images/**","/cms-rs/**","/cms/profile.js").permitAll()
                        .requestMatchers("/resource/**").permitAll()
                        .requestMatchers("/api/**").permitAll() // Permit all cho tất cả các endpoint bắt đầu bằng /api
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/process-after-login", true) // sau khi login thành công sẽ truy cập vào url process-after-login để điều hướng phân quyền
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("Quang :"+"12345"));
        System.out.println(passwordEncoder.encode("12345"));
    }
}

