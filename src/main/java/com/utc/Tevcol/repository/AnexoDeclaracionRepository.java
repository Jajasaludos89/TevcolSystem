package com.utc.Tevcol.repository;

import com.utc.Tevcol.entity.AnexoDeclaracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnexoDeclaracionRepository extends JpaRepository<AnexoDeclaracion, Long> {

    boolean existsByCodigoAnxDeclaracion(Long codigoAnxDeclaracion);

    List<AnexoDeclaracion> findByDeclaracion_CodigoDeclaracion(Long codigoDeclaracion);

    boolean existsByDeclaracion_CodigoDeclaracion(Long codigoDeclaracion);
}