package ufps.centrosrecreativos.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the cru_calificacion_centro database table.
 *
 */
@Entity
@Table(name = "cru_calificacion_centro")
public class CruCalificacionCentro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "calificacion")
    private Integer calificacion;

    private String nombre;

    private String correo;

    @Column(name = "observacion", columnDefinition = "varchar(500)")
    private String observacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private Date fechaRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private Date fechaActualizacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_centrp", referencedColumnName = "id")
    private CruCentroRecreativo centro;

    @Column(nullable = false)
    private Boolean enable;

    @PrePersist
    private void prePersist(){
        this.enable = true;
        this.fechaRegistro = new Date();
    }

    @PreUpdate
    private void preUpdate(){
        this.fechaActualizacion = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public CruCentroRecreativo getCentro() {
        return centro;
    }

    public void setCentro(CruCentroRecreativo centro) {
        this.centro = centro;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
