package ufps.centrosrecreativos.api.container;

import java.io.Serializable;

public class CentroEntradaApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombreCentro;
    private String descripcionCentro;
    private String direccionCentro;
    private String posicionX;
    private String posicionY;
    private Boolean enable;
    private Integer comunaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getDescripcionCentro() {
        return descripcionCentro;
    }

    public void setDescripcionCentro(String descripcionCentro) {
        this.descripcionCentro = descripcionCentro;
    }

    public String getDireccionCentro() {
        return direccionCentro;
    }

    public void setDireccionCentro(String direccionCentro) {
        this.direccionCentro = direccionCentro;
    }

    public String getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(String posicionX) {
        this.posicionX = posicionX;
    }

    public String getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(String posicionY) {
        this.posicionY = posicionY;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getComunaId() {
        return comunaId;
    }

    public void setComunaId(Integer comunaId) {
        this.comunaId = comunaId;
    }
}
