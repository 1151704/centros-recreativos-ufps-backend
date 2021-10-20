package ufps.centrosrecreativos.api.container;

import ufps.centrosrecreativos.api.model.CruCentroRecreativo;

import java.io.Serializable;
import java.util.List;

public class CentrosRecreativosApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<CruCentroRecreativo> centros;

    public List<CruCentroRecreativo> getCentros() {
        return centros;
    }

    public void setCentros(List<CruCentroRecreativo> centros) {
        this.centros = centros;
    }
}
