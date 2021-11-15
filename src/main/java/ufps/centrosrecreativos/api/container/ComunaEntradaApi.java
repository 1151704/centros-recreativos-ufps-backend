package ufps.centrosrecreativos.api.container;

import java.io.Serializable;

public class ComunaEntradaApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombreComuna;
    private String codigoComuna;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public String getCodigoComuna() {
        return codigoComuna;
    }

    public void setCodigoComuna(String codigoComuna) {
        this.codigoComuna = codigoComuna;
    }
}
