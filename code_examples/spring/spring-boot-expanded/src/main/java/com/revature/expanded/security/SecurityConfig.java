package com.revature.expanded.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * The '@EnableWebSecurity' annotation below is actually not
 * required for this class to work. This annotation is an explicit
 * call to Spring to import a number of configuration and support
 * classes.
 *
 * Spring boot will do this automatically even without this annotation
 * However, this annotation is required explicitly on your security
 * configuration class in order for Web/MVC slice tests
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
     * The @Order annotation determines how spring Security will handle
     * a request for different endpoints (i.e. the first SecurityFilterChain
     * that would allow a user access to the endpoint is used, based on the
     * order number provided)
     *
     * Because this SecurityFilterChain is a "catch-all" for any other endpoint
     * This will cause issues for Spring Security to process requests using
     * Method-level security.
     */
    @Bean
    @Order(3)
    public SecurityFilterChain basicChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .authenticated());
        /*
         * 'permitAll()' can be used to allow requests with no authentication
         * required
         *
         * '.authenticated()' requires that a user is authenticated in order to
         * access our application/endpoints
         */

        /*
         * After you create a SecurityFilterChain Spring's default
         * behavior backs off entirely.
         *
         * The following line re-enables the 'login form' (i.e. sending
         * requests via an HTML form)
         */
        http.formLogin(Customizer.withDefaults());

        /*
         * The following line of code is required for demonstrations
         * using Postman. Postman is not submitting an HTML form in
         * the same way that your browser would - this HTTP basic allows
         * for non-form based HTTP requests to be processed
         */
        http.httpBasic(Customizer.withDefaults());

        // Uncomment the following line(s) to see the effect of CSRF & CORS
        // Allow Cors Configuration (required for frontend application to work)
        http.cors(Customizer.withDefaults());

        /*
         * The following disables CSRF - This is really only required for Postman
         * to work correctly
         */
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }


    /*
     * Authentication with Spring Security requires a Password encoder (spring
     * expects a hashed password). The bean below registers a password encoder
     * using BCrypt - Spring Security's standard and recommended choice
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*
     * You can include multiple SecurityFilterChains for your application
     * However, only one can act as your 'fall-back' or 'catch-all' Chain.
     *
     * Any SecurityFilterChain without an explicit 'securityMatcher' will
     * act as that fall-back.
     *
     * Below, this endpoint allows anyone to access actuator
     *
     * The @Order annotation specifies in which order endpoint matchers are
     * checked - the first Filter with a matching URL is used
     */
    @Bean
    @Order(1)
    public SecurityFilterChain actuatorHealthChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/actuator/health")
                .authorizeHttpRequests( auth -> auth
                        .anyRequest()
                        .permitAll()
                );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        http.cors(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    /*
     * The following SecurityFilterChain specifies that only someone with
     * an Admin role can access the logins and other actuator endpoints
     *
     * Roles in Spring security actually look like this:
     *       "ROLE_<ROLE_VALUE>"
     *
     * A 'catch-all' for a URL-based authorization uses a double-asterisk
     * in the path pattern:
     *      '/your-path/**'
     */
    @Bean
    @Order(2)
    public SecurityFilterChain adminChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/logins/**", "/actuator/**")
                .authorizeHttpRequests(auth -> auth
                .anyRequest()
                .hasRole("ADMIN")
        );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        http.cors(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
