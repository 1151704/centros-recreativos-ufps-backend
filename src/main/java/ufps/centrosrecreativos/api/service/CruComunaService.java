package ufps.centrosrecreativos.api.service;

import ufps.centrosrecreativos.api.model.CruComuna;

import java.util.List;

public interface CruComunaService {

    public List<CruComuna> findAll();

    public CruComuna findById(Integer id);

    public CruComuna save(CruComuna entity);

}
