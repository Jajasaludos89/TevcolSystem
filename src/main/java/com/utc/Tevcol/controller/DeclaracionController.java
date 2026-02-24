package com.utc.Tevcol.controller;

import com.utc.Tevcol.entity.EncabezadoDeclaracionAnual;
import com.utc.Tevcol.service.EncabezadoDeclaracionAnualService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/declaraciones")
public class DeclaracionController {

    private final EncabezadoDeclaracionAnualService service;

    public DeclaracionController(EncabezadoDeclaracionAnualService service) {
        this.service = service;
    }

    private void flash(RedirectAttributes ra, String type, String title, String text) {
        ra.addFlashAttribute("alertType", type);
        ra.addFlashAttribute("alertTitle", title);
        ra.addFlashAttribute("alertText", text);
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("declaraciones", service.listarTodos());
        return "declaraciones/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("declaracion", new EncabezadoDeclaracionAnual());
        return "declaraciones/nuevo";
    }

    // NUEVO: endpoint para jQuery Validate remote
    // Devuelve "true" si el código está disponible, "false" si ya existe.
    // No usa ningún método nuevo del service: solo buscarPorId (ya lo tienes).
    @GetMapping(value = "/exists-codigo", produces = "text/plain")
    @ResponseBody
    public String existsCodigo(@RequestParam("codigo") Long codigo) {

        if (codigo == null) return "true";

        boolean existe = service.buscarPorId(codigo).isPresent();

        return existe ? "false" : "true";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("declaracion") EncabezadoDeclaracionAnual d,
                          RedirectAttributes ra) {

        String error = service.validarNuevo(d);
        if (error != null) {
            flash(ra, "error", "No se pudo guardar", error);
            return "redirect:/declaraciones/nuevo";
        }

        service.guardar(d);
        flash(ra, "success", "Listo", "Declaración guardada correctamente");
        return "redirect:/declaraciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {

        var opt = service.buscarPorId(id);
        if (opt.isEmpty()) {
            flash(ra, "error", "Ups", "Declaración no encontrada");
            return "redirect:/declaraciones";
        }

        model.addAttribute("declaracion", opt.get());
        return "declaraciones/editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("declaracion") EncabezadoDeclaracionAnual d,
                             RedirectAttributes ra) {

        if (d.getCodigoDeclaracion() == null || service.buscarPorId(d.getCodigoDeclaracion()).isEmpty()) {
            flash(ra, "error", "Ups", "Declaración no encontrada");
            return "redirect:/declaraciones";
        }

        String error = service.validarEdicion(d);
        if (error != null) {
            flash(ra, "error", "No se pudo actualizar", error);
            return "redirect:/declaraciones/editar/" + d.getCodigoDeclaracion();
        }

        service.guardar(d);
        flash(ra, "success", "Listo", "Declaración actualizada correctamente");
        return "redirect:/declaraciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {

        String error = service.validarEliminacion(id);
        if (error != null) {
            flash(ra, "warning", "No se puede eliminar", error);
            return "redirect:/declaraciones";
        }

        service.eliminar(id);
        flash(ra, "success", "Listo", "Declaración eliminada correctamente");
        return "redirect:/declaraciones";
    }
}