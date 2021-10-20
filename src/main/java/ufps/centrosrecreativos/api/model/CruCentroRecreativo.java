package ufps.centrosrecreativos.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the cru_centro_recreativo database table.
 * 
 */
@Entity
@Table(name = "cru_centro_recreativo")
public class CruCentroRecreativo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre_centro")
	private String nombreCentro;

	@Column(name = "descripcion_centro")
	private String descripcionCentro;

	@Column(name = "direccion_centro")
	private String direccionCentro;

	@Column(name = "posicion_x")
	private String posicionX;

	@Column(name = "posicion_y")
	private String posicionY;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
	private Date fechaRegistro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion")
	@JsonIgnore
	private Date fechaActualizacion;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_comuna", referencedColumnName = "id")
	private CruComuna comunaId;

	public CruCentroRecreativo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public String getDescripcionCentro() {
		return descripcionCentro;
	}

	public void setDescripcionCentro(String descripcionCentro) {
		this.descripcionCentro = descripcionCentro;
	}

	public String getDireccionCentro() {
		return direccionCentro;
	}

	public void setDireccionCentro(String direccionCentro) {
		this.direccionCentro = direccionCentro;
	}

	public String getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(String posicionX) {
		this.posicionX = posicionX;
	}

	public String getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(String posicionY) {
		this.posicionY = posicionY;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public CruComuna getComunaId() {
		return comunaId;
	}

	public void setComunaId(CruComuna comunaId) {
		this.comunaId = comunaId;
	}
}