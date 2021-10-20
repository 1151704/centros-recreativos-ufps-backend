package ufps.centrosrecreativos.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The persistent class for the cru_departamento database table.
 * 
 */
@Entity
@Table(name = "cru_departamento")
public class CruDepartamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_departamento")
	private Integer idDepartamento;

	@Column(name = "codigo_departamento")
	private String codigoDepartamento;

	@Column(name = "nombre_departamento")
	private String nombreDepartamento;

	public CruDepartamento() {
	}

	public Integer getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getCodigoDepartamento() {
		return this.codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

}