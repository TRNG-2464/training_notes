package com.revature.expanded.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    /*
     * Note: Spring Security automatically provides a 'logout' endpoint
     * http://localhost:8080/logout
     *
     * however, you can create a custom logout endpoint if you want to
     * provide more control over how you handle this logic yourself
     */
    @PostMapping("api/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logged Out successfully");
    }
}
