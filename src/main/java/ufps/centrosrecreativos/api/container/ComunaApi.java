package ufps.centrosrecreativos.api.container;

import ufps.centrosrecreativos.api.model.CruComuna;

import java.io.Serializable;
import java.util.List;

public class ComunaApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private CruComuna comuna;

    public CruComuna getComuna() {
        return comuna;
    }

    public void setComuna(CruComuna comuna) {
        this.comuna = comuna;
    }
}
