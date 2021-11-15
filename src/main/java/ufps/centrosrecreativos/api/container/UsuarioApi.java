package ufps.centrosrecreativos.api.container;

import ufps.centrosrecreativos.api.model.CruUsuario;

import java.io.Serializable;

public class UsuarioApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private CruUsuario usuario;

    public CruUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(CruUsuario usuario) {
        this.usuario = usuario;
    }
}
