package com.utc.Tevcol.repository;

import com.utc.Tevcol.entity.EncabezadoDeclaracionAnual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncabezadoDeclaracionAnualRepository extends JpaRepository<EncabezadoDeclaracionAnual, Long> {

    boolean existsByCodigoDeclaracion(Long codigoDeclaracion);

    boolean existsByAnioDeclaracionAndVersionDeclaracion(String anioDeclaracion, String versionDeclaracion);
    boolean existsByAnioDeclaracionAndVersionDeclaracionAndCodigoDeclaracionNot(
            String anioDeclaracion, String versionDeclaracion, Long codigoDeclaracion
    );
}