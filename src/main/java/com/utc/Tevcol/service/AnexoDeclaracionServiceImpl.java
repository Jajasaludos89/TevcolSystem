package com.utc.Tevcol.service;

import com.utc.Tevcol.entity.AnexoDeclaracion;
import com.utc.Tevcol.repository.AnexoDeclaracionRepository;
import com.utc.Tevcol.repository.EncabezadoDeclaracionAnualRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnexoDeclaracionServiceImpl implements AnexoDeclaracionService {

    private final AnexoDeclaracionRepository repo;
    private final EncabezadoDeclaracionAnualRepository declaracionRepo;

    public AnexoDeclaracionServiceImpl(
            AnexoDeclaracionRepository repo,
            EncabezadoDeclaracionAnualRepository declaracionRepo
    ) {
        this.repo = repo;
        this.declaracionRepo = declaracionRepo;
    }

    @Override
    public List<AnexoDeclaracion> listarTodos() {
        return repo.findAll();
    }

    @Override
    public List<AnexoDeclaracion> listarPorDeclaracion(Long codigoDeclaracion) {
        return repo.findByDeclaracion_CodigoDeclaracion(codigoDeclaracion);
    }

    @Override
    public Optional<AnexoDeclaracion> buscarPorId(Long id) {
        return repo.findById(id);
    }

    @Override
    public AnexoDeclaracion guardar(AnexoDeclaracion a) {
        return repo.save(a);
    }

    @Override
    public void eliminar(Long id) {
        String error = validarEliminacion(id);
        if (error != null) throw new IllegalStateException(error);
        repo.deleteById(id);
    }

    private boolean vacio(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public String validarNuevo(AnexoDeclaracion a, Long fkCodDeclaracion) {

        if (a.getCodigoAnxDeclaracion() == null) return "El código del anexo es obligatorio (no es autoincremental).";
        if (repo.existsById(a.getCodigoAnxDeclaracion())) return "Ya existe un anexo con ese código.";

        if (fkCodDeclaracion == null) return "Debes seleccionar una declaración (fk_cod_declaracion).";
        var decOpt = declaracionRepo.findById(fkCodDeclaracion);
        if (decOpt.isEmpty()) return "La declaración seleccionada no existe.";

        if (vacio(a.getNombreAnxDeclaracion())) return "El nombre del anexo es obligatorio.";

        // asigna relación (clave para que guarde el FK)
        a.setDeclaracion(decOpt.get());

        return null;
    }

    @Override
    public String validarEdicion(AnexoDeclaracion a, Long fkCodDeclaracion) {

        if (a.getCodigoAnxDeclaracion() == null) return "ID inválido.";
        if (repo.findById(a.getCodigoAnxDeclaracion()).isEmpty()) return "Anexo no encontrado.";

        if (fkCodDeclaracion == null) return "Debes seleccionar una declaración (fk_cod_declaracion).";
        var decOpt = declaracionRepo.findById(fkCodDeclaracion);
        if (decOpt.isEmpty()) return "La declaración seleccionada no existe.";

        if (vacio(a.getNombreAnxDeclaracion())) return "El nombre del anexo es obligatorio.";

        a.setDeclaracion(decOpt.get());

        return null;
    }

    @Override
    public String validarEliminacion(Long id) {

        if (id == null) return "ID inválido.";
        if (repo.findById(id).isEmpty()) return "Anexo no encontrado.";

        return null;
    }
}