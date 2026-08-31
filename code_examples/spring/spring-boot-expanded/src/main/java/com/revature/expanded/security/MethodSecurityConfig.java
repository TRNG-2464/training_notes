package com.revature.expanded.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/*
 * The 'EnableMethodSecurity' annotation is required to allow the use of
 * the '@PreAuthorize' annotation anywhere in the project
 */
@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {
}
