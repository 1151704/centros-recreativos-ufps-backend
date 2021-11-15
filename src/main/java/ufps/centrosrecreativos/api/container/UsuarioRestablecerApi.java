package ufps.centrosrecreativos.api.container;

import java.io.Serializable;

public class UsuarioRestablecerApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private String identificacion;
    private String correo;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
