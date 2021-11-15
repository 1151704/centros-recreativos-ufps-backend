package ufps.centrosrecreativos.api.repository;

import org.springframework.data.repository.CrudRepository;
import ufps.centrosrecreativos.api.model.CruCentroRecreativo;

import java.util.List;

public interface CruCentroRecreativoRepository extends CrudRepository<CruCentroRecreativo, Integer> {

    public List<CruCentroRecreativo> findByEnable(Boolean enable);

}
