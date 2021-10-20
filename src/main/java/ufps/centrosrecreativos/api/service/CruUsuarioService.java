package ufps.centrosrecreativos.api.service;

import ufps.centrosrecreativos.api.model.CruUsuario;

import java.util.List;

public interface CruUsuarioService {

	public List<CruUsuario> findAll();

	public CruUsuario save(CruUsuario entity);

	public CruUsuario findByUsernameAndIdentificacion(String username, String identificacion);

	public CruUsuario findByUsername(String username);

	public CruUsuario findByIdentificacion(String identificacion);

	public boolean existByUsernameAndIdentificacion(String username, String identificacion);

}
