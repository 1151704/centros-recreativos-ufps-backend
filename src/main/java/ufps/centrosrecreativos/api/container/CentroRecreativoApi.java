package ufps.centrosrecreativos.api.container;

import ufps.centrosrecreativos.api.model.CruCentroRecreativo;

import java.io.Serializable;

public class CentroRecreativoApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private CruCentroRecreativo centro;

    public CruCentroRecreativo getCentro() {
        return centro;
    }

    public void setCentro(CruCentroRecreativo centro) {
        this.centro = centro;
    }
}
