package ufps.centrosrecreativos.api.repository;

import org.springframework.data.repository.CrudRepository;
import ufps.centrosrecreativos.api.model.CruTipoIdentificacion;

public interface CruTipoIdentificacionRepository extends CrudRepository<CruTipoIdentificacion, Integer> {

	public CruTipoIdentificacion findByTipo(String tipo);
	
}
