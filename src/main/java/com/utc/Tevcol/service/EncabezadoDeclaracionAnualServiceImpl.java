package com.utc.Tevcol.service;

import com.utc.Tevcol.entity.EncabezadoDeclaracionAnual;
import com.utc.Tevcol.repository.AnexoDeclaracionRepository;
import com.utc.Tevcol.repository.EncabezadoDeclaracionAnualRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncabezadoDeclaracionAnualServiceImpl implements EncabezadoDeclaracionAnualService {

    private final EncabezadoDeclaracionAnualRepository repo;
    private final AnexoDeclaracionRepository anexoRepo;

    public EncabezadoDeclaracionAnualServiceImpl(
            EncabezadoDeclaracionAnualRepository repo,
            AnexoDeclaracionRepository anexoRepo
    ) {
        this.repo = repo;
        this.anexoRepo = anexoRepo;
    }

    @Override
    public List<EncabezadoDeclaracionAnual> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Optional<EncabezadoDeclaracionAnual> buscarPorId(Long id) {
        return repo.findById(id);
    }

    @Override
    public EncabezadoDeclaracionAnual guardar(EncabezadoDeclaracionAnual d) {
        return repo.save(d);
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
    public String validarNuevo(EncabezadoDeclaracionAnual d) {

        if (d.getCodigoDeclaracion() == null) return "El código de declaración es obligatorio (no es autoincremental).";
        if (repo.existsById(d.getCodigoDeclaracion())) return "Ya existe una declaración con ese código.";

        if (vacio(d.getAnioDeclaracion())) return "El año de declaración es obligatorio.";
        if (vacio(d.getEstadoDeclaracion())) return "El estado es obligatorio.";
        if (vacio(d.getVersionDeclaracion())) return "La versión es obligatoria.";

        // regla opcional (si no quieres, la quitas):
        if (repo.existsByAnioDeclaracionAndVersionDeclaracion(d.getAnioDeclaracion(), d.getVersionDeclaracion())) {
            return "Ya existe una declaración con ese año y versión.";
        }

        return null;
    }

    @Override
    public String validarEdicion(EncabezadoDeclaracionAnual d) {

        if (d.getCodigoDeclaracion() == null) return "ID inválido.";
        if (repo.findById(d.getCodigoDeclaracion()).isEmpty()) return "Declaración no encontrada.";

        if (vacio(d.getAnioDeclaracion())) return "El año de declaración es obligatorio.";
        if (vacio(d.getEstadoDeclaracion())) return "El estado es obligatorio.";
        if (vacio(d.getVersionDeclaracion())) return "La versión es obligatoria.";

        // regla opcional:
        if (repo.existsByAnioDeclaracionAndVersionDeclaracionAndCodigoDeclaracionNot(
                d.getAnioDeclaracion(), d.getVersionDeclaracion(), d.getCodigoDeclaracion()
        )) {
            return "Ya existe otra declaración con ese año y versión.";
        }

        return null;
    }

    @Override
    public String validarEliminacion(Long id) {

        if (id == null) return "ID inválido.";
        if (repo.findById(id).isEmpty()) return "Declaración no encontrada.";

        // regla clave: si tiene anexos, no se borra
        if (anexoRepo.existsByDeclaracion_CodigoDeclaracion(id)) {
            return "No se puede eliminar: esta declaración tiene anexos registrados.";
        }

        return null;
    }
}