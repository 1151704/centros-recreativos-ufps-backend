package ufps.centrosrecreativos.api.container;

import ufps.centrosrecreativos.api.model.CruComuna;

import java.io.Serializable;
import java.util.List;

public class ComunasApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<CruComuna> comunas;

    public List<CruComuna> getComunas() {
        return comunas;
    }

    public void setComunas(List<CruComuna> comunas) {
        this.comunas = comunas;
    }
}
