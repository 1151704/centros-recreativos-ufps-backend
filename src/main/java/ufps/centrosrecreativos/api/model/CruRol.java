package ufps.centrosrecreativos.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the cru_rol database table.
 * 
 */
@Entity
@Table(name = "cru_rol")
public class CruRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;

	@Column(nullable = false)
	private String descripcion;

	@Column(nullable = false, unique = true)
	private String rol;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", descripcion=" + descripcion + ", rol=" + rol + "]";
	}

}
