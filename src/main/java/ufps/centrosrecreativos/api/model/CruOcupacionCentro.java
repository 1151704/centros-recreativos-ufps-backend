package ufps.centrosrecreativos.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the cru_ocupacion_centro database table.
 *
 */
@Entity
@Table(name = "cru_ocupacion_centro")
public class CruOcupacionCentro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean ocupado;

    private String nombre;

    private String correo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private Date fechaRegistro;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ocupacion", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaOcupacion;

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
        this.enable = false;
        this.fechaRegistro = new Date();
        this.fechaOcupacion = new Date();
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

    public Boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
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

    public Date getFechaOcupacion() {
        return fechaOcupacion;
    }

    public void setFechaOcupacion(Date fechaOcupacion) {
        this.fechaOcupacion = fechaOcupacion;
    }
}
