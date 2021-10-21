package ufps.centrosrecreativos.api.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the cru_municipio database table.
 * 
 */
//@Entity
//@Table(name = "cru_municipio")
public class CruMunicipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_municipio")
	private Integer idMunicipio;

	@Column(name = "codigo_departamento")
	private String codigoDepartamento;

	@Column(name = "codigo_dpto_mpio")
	private String codigoDptoMpio;

	@Column(name = "codigo_municipio")
	private String codigoMunicipio;

	@Column(name = "nombre_departamento")
	private String nombreDepartamento;

	@Column(name = "nombre_municipio")
	private String nombreMunicipio;

	@ManyToOne
	@JoinColumn(name = "id_departamento")
	private CruDepartamento departamento;

	public Integer getIdMunicipio() {
		return this.idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getCodigoDepartamento() {
		return this.codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getCodigoDptoMpio() {
		return this.codigoDptoMpio;
	}

	public void setCodigoDptoMpio(String codigoDptoMpio) {
		this.codigoDptoMpio = codigoDptoMpio;
	}

	public String getCodigoMunicipio() {
		return this.codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public String getNombreMunicipio() {
		return this.nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	public CruDepartamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(CruDepartamento departamento) {
		this.departamento = departamento;
	}

}
