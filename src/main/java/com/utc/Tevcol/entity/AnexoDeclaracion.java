package com.utc.Tevcol.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "anexo_declaracion")
public class AnexoDeclaracion {

    @Id
    @Column(name = "codigo_anx_declaracion")
    private Long codigoAnxDeclaracion;

    @Column(name = "fecha_anx_declaracion")
    private LocalDate fechaAnxDeclaracion;

    @Column(name = "nombre_anx_declaracion")
    private String nombreAnxDeclaracion;

    @Column(name = "descripcion_anx_declaracion")
    private String descripcionAnxDeclaracion;

    @Column(name = "link_archivo_anx_declaracion")
    private String linkArchivoAnxDeclaracion;

    // relación (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cod_declaracion")
    private EncabezadoDeclaracionAnual declaracion;

    @Column(name = "fecha_creado_anx_declaracion", updatable = false)
    private LocalDateTime fechaCreadoAnxDeclaracion;

    @Column(name = "fecha_editado_anx_declaracion")
    private LocalDateTime fechaEditadoAnxDeclaracion;

    // ===== CAMBIO: Hooks JPA para evitar NULL en timestamps =====
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (fechaCreadoAnxDeclaracion == null) fechaCreadoAnxDeclaracion = now;
        if (fechaEditadoAnxDeclaracion == null) fechaEditadoAnxDeclaracion = now;
    }

    @PreUpdate
    protected void onUpdate() {
        fechaEditadoAnxDeclaracion = LocalDateTime.now();
    }
    // ===========================================================

    // getters & setters

    public Long getCodigoAnxDeclaracion() { return codigoAnxDeclaracion; }
    public void setCodigoAnxDeclaracion(Long codigoAnxDeclaracion) { this.codigoAnxDeclaracion = codigoAnxDeclaracion; }

    public LocalDate getFechaAnxDeclaracion() { return fechaAnxDeclaracion; }
    public void setFechaAnxDeclaracion(LocalDate fechaAnxDeclaracion) { this.fechaAnxDeclaracion = fechaAnxDeclaracion; }

    public String getNombreAnxDeclaracion() { return nombreAnxDeclaracion; }
    public void setNombreAnxDeclaracion(String nombreAnxDeclaracion) { this.nombreAnxDeclaracion = nombreAnxDeclaracion; }

    public String getDescripcionAnxDeclaracion() { return descripcionAnxDeclaracion; }
    public void setDescripcionAnxDeclaracion(String descripcionAnxDeclaracion) { this.descripcionAnxDeclaracion = descripcionAnxDeclaracion; }

    public String getLinkArchivoAnxDeclaracion() { return linkArchivoAnxDeclaracion; }
    public void setLinkArchivoAnxDeclaracion(String linkArchivoAnxDeclaracion) { this.linkArchivoAnxDeclaracion = linkArchivoAnxDeclaracion; }

    public EncabezadoDeclaracionAnual getDeclaracion() { return declaracion; }
    public void setDeclaracion(EncabezadoDeclaracionAnual declaracion) { this.declaracion = declaracion; }

    public LocalDateTime getFechaCreadoAnxDeclaracion() { return fechaCreadoAnxDeclaracion; }
    public void setFechaCreadoAnxDeclaracion(LocalDateTime fechaCreadoAnxDeclaracion) { this.fechaCreadoAnxDeclaracion = fechaCreadoAnxDeclaracion; }

    public LocalDateTime getFechaEditadoAnxDeclaracion() { return fechaEditadoAnxDeclaracion; }
    public void setFechaEditadoAnxDeclaracion(LocalDateTime fechaEditadoAnxDeclaracion) { this.fechaEditadoAnxDeclaracion = fechaEditadoAnxDeclaracion; }
}