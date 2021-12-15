package ufps.centrosrecreativos.api.repository;

import org.springframework.data.repository.CrudRepository;
import ufps.centrosrecreativos.api.model.CruCentroRecreativo;
import ufps.centrosrecreativos.api.model.CruOcupacionCentro;

import java.util.Date;
import java.util.List;

public interface CruOcupacionCentroRepository extends CrudRepository<CruOcupacionCentro, Integer> {

    List<CruOcupacionCentro> findByCentroAndFechaOcupacion(CruCentroRecreativo centro, Date fechaOcupacion);

}
