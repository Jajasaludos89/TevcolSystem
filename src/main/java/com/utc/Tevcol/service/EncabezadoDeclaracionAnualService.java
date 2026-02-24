package com.utc.Tevcol.service;

import com.utc.Tevcol.entity.EncabezadoDeclaracionAnual;

import java.util.List;
import java.util.Optional;

public interface EncabezadoDeclaracionAnualService {

    List<EncabezadoDeclaracionAnual> listarTodos();
    Optional<EncabezadoDeclaracionAnual> buscarPorId(Long id);
    EncabezadoDeclaracionAnual guardar(EncabezadoDeclaracionAnual d);
    void eliminar(Long id);

    String validarNuevo(EncabezadoDeclaracionAnual d);
    String validarEdicion(EncabezadoDeclaracionAnual d);
    String validarEliminacion(Long id);
}