package com.utc.Tevcol.service;

import com.utc.Tevcol.entity.AnexoDeclaracion;

import java.util.List;
import java.util.Optional;

public interface AnexoDeclaracionService {

    List<AnexoDeclaracion> listarTodos();
    List<AnexoDeclaracion> listarPorDeclaracion(Long codigoDeclaracion);

    Optional<AnexoDeclaracion> buscarPorId(Long id);
    AnexoDeclaracion guardar(AnexoDeclaracion a);
    void eliminar(Long id);

    String validarNuevo(AnexoDeclaracion a, Long fkCodDeclaracion);
    String validarEdicion(AnexoDeclaracion a, Long fkCodDeclaracion);
    String validarEliminacion(Long id);
}