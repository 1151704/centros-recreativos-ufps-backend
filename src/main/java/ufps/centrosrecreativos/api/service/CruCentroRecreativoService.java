package ufps.centrosrecreativos.api.service;

import ufps.centrosrecreativos.api.model.CruCentroRecreativo;

import java.util.List;

public interface CruCentroRecreativoService {

    public List<CruCentroRecreativo> findAll();

    public List<CruCentroRecreativo> findEnable();

    public CruCentroRecreativo findById(Integer id);

    public CruCentroRecreativo save(CruCentroRecreativo entity);

}
