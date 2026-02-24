package com.utc.Tevcol.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "encabezado_declaracion_anual")
public class EncabezadoDeclaracionAnual {

    @Id
    @Column(name = "codigo_declaracion")
    private Long codigoDeclaracion;

    @Column(name = "anio_declaracion")
    private String anioDeclaracion;

    @Column(name = "estado_declaracion")
    private String estadoDeclaracion;

    @Column(name = "version_declaracion")
    private String versionDeclaracion;

    @Column(name = "fecha_declaracion")
    private LocalDate fechaDeclaracion;

    @Column(name = "fecha_aprobacion_declaracion")
    private LocalDate fechaAprobacionDeclaracion;

    @Column(name = "fecha_obtencion_registro_declaracion")
    private LocalDate fechaObtencionRegistroDeclaracion;

    @Column(name = "reponsable_tecnico_declaracion")
    private String reponsableTecnicoDeclaracion;

    @Column(name = "nombre_razon_social_declaracion")
    private String nombreRazonSocialDeclaracion;

    @Column(name = "version_final")
    private String versionFinal;

    @Column(name = "fk_cod_registro")
    private Long fkCodRegistro;

    @Column(name = "n_licencia_empresa_declaracion")
    private String nLicenciaEmpresaDeclaracion;

    @Column(name = "fk_codigo_emp")
    private Long fkCodigoEmp;

    @Column(name = "n_licencia_ambiental_prestador_servicio")
    private String nLicenciaAmbientalPrestadorServicio;

    @Column(name = "fecha_creado_declaracion", updatable = false)
    private LocalDateTime fechaCreadoDeclaracion;

    @Column(name = "fecha_editado_declaracion")
    private LocalDateTime fechaEditadoDeclaracion;

    @Column(name = "fk_cod_establecimiento")
    private Long fkCodEstablecimiento;

    // Hooks JPA: nunca deja null los timestamps si la BD los exige
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (fechaCreadoDeclaracion == null) fechaCreadoDeclaracion = now;
        if (fechaEditadoDeclaracion == null) fechaEditadoDeclaracion = now;
    }

    @PreUpdate
    protected void onUpdate() {
        fechaEditadoDeclaracion = LocalDateTime.now();
    }

    // getters & setters

    public Long getCodigoDeclaracion() { return codigoDeclaracion; }
    public void setCodigoDeclaracion(Long codigoDeclaracion) { this.codigoDeclaracion = codigoDeclaracion; }

    public String getAnioDeclaracion() { return anioDeclaracion; }
    public void setAnioDeclaracion(String anioDeclaracion) { this.anioDeclaracion = anioDeclaracion; }

    public String getEstadoDeclaracion() { return estadoDeclaracion; }
    public void setEstadoDeclaracion(String estadoDeclaracion) { this.estadoDeclaracion = estadoDeclaracion; }

    public String getVersionDeclaracion() { return versionDeclaracion; }
    public void setVersionDeclaracion(String versionDeclaracion) { this.versionDeclaracion = versionDeclaracion; }

    public LocalDate getFechaDeclaracion() { return fechaDeclaracion; }
    public void setFechaDeclaracion(LocalDate fechaDeclaracion) { this.fechaDeclaracion = fechaDeclaracion; }

    public LocalDate getFechaAprobacionDeclaracion() { return fechaAprobacionDeclaracion; }
    public void setFechaAprobacionDeclaracion(LocalDate fechaAprobacionDeclaracion) { this.fechaAprobacionDeclaracion = fechaAprobacionDeclaracion; }

    public LocalDate getFechaObtencionRegistroDeclaracion() { return fechaObtencionRegistroDeclaracion; }
    public void setFechaObtencionRegistroDeclaracion(LocalDate fechaObtencionRegistroDeclaracion) { this.fechaObtencionRegistroDeclaracion = fechaObtencionRegistroDeclaracion; }

    public String getReponsableTecnicoDeclaracion() { return reponsableTecnicoDeclaracion; }
    public void setReponsableTecnicoDeclaracion(String reponsableTecnicoDeclaracion) { this.reponsableTecnicoDeclaracion = reponsableTecnicoDeclaracion; }

    public String getNombreRazonSocialDeclaracion() { return nombreRazonSocialDeclaracion; }
    public void setNombreRazonSocialDeclaracion(String nombreRazonSocialDeclaracion) { this.nombreRazonSocialDeclaracion = nombreRazonSocialDeclaracion; }

    public String getVersionFinal() { return versionFinal; }
    public void setVersionFinal(String versionFinal) { this.versionFinal = versionFinal; }

    public Long getFkCodRegistro() { return fkCodRegistro; }
    public void setFkCodRegistro(Long fkCodRegistro) { this.fkCodRegistro = fkCodRegistro; }

    public String getNLicenciaEmpresaDeclaracion() { return nLicenciaEmpresaDeclaracion; }
    public void setNLicenciaEmpresaDeclaracion(String nLicenciaEmpresaDeclaracion) { this.nLicenciaEmpresaDeclaracion = nLicenciaEmpresaDeclaracion; }

    public Long getFkCodigoEmp() { return fkCodigoEmp; }
    public void setFkCodigoEmp(Long fkCodigoEmp) { this.fkCodigoEmp = fkCodigoEmp; }

    public String getNLicenciaAmbientalPrestadorServicio() { return nLicenciaAmbientalPrestadorServicio; }
    public void setNLicenciaAmbientalPrestadorServicio(String nLicenciaAmbientalPrestadorServicio) { this.nLicenciaAmbientalPrestadorServicio = nLicenciaAmbientalPrestadorServicio; }

    public LocalDateTime getFechaCreadoDeclaracion() { return fechaCreadoDeclaracion; }
    public void setFechaCreadoDeclaracion(LocalDateTime fechaCreadoDeclaracion) { this.fechaCreadoDeclaracion = fechaCreadoDeclaracion; }

    public LocalDateTime getFechaEditadoDeclaracion() { return fechaEditadoDeclaracion; }
    public void setFechaEditadoDeclaracion(LocalDateTime fechaEditadoDeclaracion) { this.fechaEditadoDeclaracion = fechaEditadoDeclaracion; }

    public Long getFkCodEstablecimiento() { return fkCodEstablecimiento; }
    public void setFkCodEstablecimiento(Long fkCodEstablecimiento) { this.fkCodEstablecimiento = fkCodEstablecimiento; }
}