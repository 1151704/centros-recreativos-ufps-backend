package ufps.centrosrecreativos.api.repository;

import org.springframework.data.repository.CrudRepository;
import ufps.centrosrecreativos.api.model.CruUsuario;

import java.util.List;

public interface CruUsuarioRepository extends CrudRepository<CruUsuario, Integer> {

	public CruUsuario findByUsernameIgnoreCaseAndIdentificacion(String username, String identificacion);

	public CruUsuario findByUsernameIgnoreCase(String username);

	public CruUsuario findByIdentificacion(String identificacion);

	public boolean existsByUsernameIgnoreCaseAndIdentificacion(String username, String identificacion);

}
