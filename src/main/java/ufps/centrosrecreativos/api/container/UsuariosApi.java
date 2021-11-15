package ufps.centrosrecreativos.api.container;

import ufps.centrosrecreativos.api.model.CruUsuario;

import java.io.Serializable;
import java.util.List;

public class UsuariosApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<CruUsuario> usuarios;

    public List<CruUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<CruUsuario> usuarios) {
        this.usuarios = usuarios;
    }
}
