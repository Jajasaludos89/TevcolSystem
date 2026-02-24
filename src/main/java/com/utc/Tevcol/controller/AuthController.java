package com.utc.Tevcol.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class AuthController {

    private String usuarioActual = "adminutc.javier";
    private String passwordActual = "tatsumaki89*";

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

        if (usuarioActual.equals(username) &&
            passwordActual.equals(password)) {

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

    // -------- RECUPERAR --------

    @GetMapping("/recuperar")
    public String recuperar(HttpSession session) {

        if (session.getAttribute("codigoRecuperacion") == null) {
            int codigo = new Random().nextInt(9000) + 1000;
            session.setAttribute("codigoRecuperacion", codigo);
        }

        return "auth/recuperar";
    }

    @PostMapping("/recuperar")
    public String procesarRecuperacion(@RequestParam String username,
                                       @RequestParam String nuevaPassword,
                                       @RequestParam int codigoIngresado,
                                       HttpSession session) {

        Integer codigoSesion = (Integer) session.getAttribute("codigoRecuperacion");

        if (codigoSesion != null &&
            codigoSesion == codigoIngresado &&
            usuarioActual.equals(username)) {

            passwordActual = nuevaPassword;
            session.removeAttribute("codigoRecuperacion");
            return "redirect:/login?resetok";
        }

        return "redirect:/recuperar?error";
    }
}