package ufps.centrosrecreativos.api.repository;

import org.springframework.data.repository.CrudRepository;
import ufps.centrosrecreativos.api.model.CruCalificacionCentro;
import ufps.centrosrecreativos.api.model.CruCentroRecreativo;
import ufps.centrosrecreativos.api.model.CruOcupacionCentro;

import java.util.Date;
import java.util.List;

public interface CruCalificacionCentroRepository extends CrudRepository<CruCalificacionCentro, Integer> {

    List<CruCalificacionCentro> findByCentroAndEnable(CruCentroRecreativo centro, Boolean enable);

}
