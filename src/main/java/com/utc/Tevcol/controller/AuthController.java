package com.utc.Tevcol.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @GetMapping("/")
    public String rootRedirect(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/declaraciones";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session) {

        if ("adminutc.javier".equals(username) &&
            "tatsumaki89*".equals(password)) {

            session.setAttribute("usuarioLogueado", username);
            return "redirect:/declaraciones";
        }

        return "redirect:/login?error";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}