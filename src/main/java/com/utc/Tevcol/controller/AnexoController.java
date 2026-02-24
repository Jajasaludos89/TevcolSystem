package com.utc.Tevcol.controller;

import com.utc.Tevcol.entity.AnexoDeclaracion;
import com.utc.Tevcol.service.AnexoDeclaracionService;
import com.utc.Tevcol.service.EncabezadoDeclaracionAnualService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/anexos")
public class AnexoController {

    private final AnexoDeclaracionService service;
    private final EncabezadoDeclaracionAnualService declaracionService;

    public AnexoController(AnexoDeclaracionService service,
                           EncabezadoDeclaracionAnualService declaracionService) {
        this.service = service;
        this.declaracionService = declaracionService;
    }

    private void flash(RedirectAttributes ra, String type, String title, String text) {
        ra.addFlashAttribute("alertType", type);
        ra.addFlashAttribute("alertTitle", title);
        ra.addFlashAttribute("alertText", text);
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("anexos", service.listarTodos());
        return "anexos/listar";
    }

    @GetMapping("/declaracion/{codigoDeclaracion}")
    public String listarPorDeclaracion(@PathVariable Long codigoDeclaracion, Model model, RedirectAttributes ra) {

        var decOpt = declaracionService.buscarPorId(codigoDeclaracion);
        if (decOpt.isEmpty()) {
            flash(ra, "error", "Ups", "Declaración no encontrada");
            return "redirect:/declaraciones";
        }

        model.addAttribute("declaracion", decOpt.get());
        model.addAttribute("anexos", service.listarPorDeclaracion(codigoDeclaracion));
        return "anexos/listarPorDeclaracion";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("anexo", new AnexoDeclaracion());
        model.addAttribute("declaraciones", declaracionService.listarTodos()); // combo para fk
        return "anexos/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("anexo") AnexoDeclaracion a,
                          @RequestParam("fkCodDeclaracion") Long fkCodDeclaracion,
                          RedirectAttributes ra) {

        String error = service.validarNuevo(a, fkCodDeclaracion);
        if (error != null) {
            flash(ra, "error", "No se pudo guardar", error);
            return "redirect:/anexos/nuevo";
        }

        service.guardar(a);
        flash(ra, "success", "Listo", "Anexo guardado correctamente");
        return "redirect:/anexos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {

        var opt = service.buscarPorId(id);
        if (opt.isEmpty()) {
            flash(ra, "error", "Ups", "Anexo no encontrado");
            return "redirect:/anexos";
        }

        model.addAttribute("anexo", opt.get());
        model.addAttribute("declaraciones", declaracionService.listarTodos());
        return "anexos/editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("anexo") AnexoDeclaracion a,
                             @RequestParam("fkCodDeclaracion") Long fkCodDeclaracion,
                             RedirectAttributes ra) {

        if (a.getCodigoAnxDeclaracion() == null || service.buscarPorId(a.getCodigoAnxDeclaracion()).isEmpty()) {
            flash(ra, "error", "Ups", "Anexo no encontrado");
            return "redirect:/anexos";
        }

        String error = service.validarEdicion(a, fkCodDeclaracion);
        if (error != null) {
            flash(ra, "error", "No se pudo actualizar", error);
            return "redirect:/anexos/editar/" + a.getCodigoAnxDeclaracion();
        }

        service.guardar(a);
        flash(ra, "success", "Listo", "Anexo actualizado correctamente");
        return "redirect:/anexos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {

        String error = service.validarEliminacion(id);
        if (error != null) {
            flash(ra, "warning", "No se puede eliminar", error);
            return "redirect:/anexos";
        }

        service.eliminar(id);
        flash(ra, "success", "Listo", "Anexo eliminado correctamente");
        return "redirect:/anexos";
    }
}