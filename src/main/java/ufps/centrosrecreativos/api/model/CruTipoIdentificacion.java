package ufps.centrosrecreativos.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the cru_tipo_identificacion database table.
 * 
 */
@Entity
@Table(name = "cru_tipo_identificacion")
public class CruTipoIdentificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;

	@Column(nullable = false)
	private String descripcion;

	@Column(nullable = false, unique = true)
	private String tipo;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoIdentificacion [id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipo + "]";
	}

}
