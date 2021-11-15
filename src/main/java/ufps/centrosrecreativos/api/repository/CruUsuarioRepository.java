package ufps.centrosrecreativos.api.repository;

import org.springframework.data.repository.CrudRepository;
import ufps.centrosrecreativos.api.model.CruRol;
import ufps.centrosrecreativos.api.model.CruUsuario;

import java.util.List;

public interface CruUsuarioRepository extends CrudRepository<CruUsuario, Integer> {

	public List<CruUsuario> findByRol(CruRol rol);

	public CruUsuario findByUsernameIgnoreCaseAndIdentificacion(String username, String identificacion);

	public CruUsuario findByUsernameIgnoreCase(String username);

	public CruUsuario findByIdentificacion(String identificacion);

	public CruUsuario findByEmail(String email);

	public boolean existsByUsernameIgnoreCaseAndIdentificacion(String username, String identificacion);

}
