package ufps.centrosrecreativos.api.service;

import ufps.centrosrecreativos.api.model.CruRol;
import ufps.centrosrecreativos.api.model.CruUsuario;

import java.util.List;

public interface CruUsuarioService {

	public List<CruUsuario> findAll();

	public List<CruUsuario> findByRol(CruRol rol);

	public CruUsuario save(CruUsuario entity);

	public CruUsuario findByUsernameAndIdentificacion(String username, String identificacion);

	public CruUsuario findByUsername(String username);

	public CruUsuario findByEmail(String email);

	public CruUsuario findByIdentificacion(String identificacion);

	public boolean existByUsernameAndIdentificacion(String username, String identificacion);

}
