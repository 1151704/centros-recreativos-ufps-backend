package ufps.centrosrecreativos.api.repository;

import org.springframework.data.repository.CrudRepository;
import ufps.centrosrecreativos.api.model.CruRol;

public interface CruRolRepository extends CrudRepository<CruRol, Integer> {

	public CruRol findByRol(String rol);

}
