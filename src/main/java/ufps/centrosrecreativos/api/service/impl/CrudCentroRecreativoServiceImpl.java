package ufps.centrosrecreativos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.centrosrecreativos.api.model.CruCentroRecreativo;
import ufps.centrosrecreativos.api.repository.CruCentroRecreativoRepository;
import ufps.centrosrecreativos.api.service.CruCentroRecreativoService;

import java.util.List;

@Service
public class CrudCentroRecreativoServiceImpl implements CruCentroRecreativoService {

    @Autowired
    private CruCentroRecreativoRepository repository;

    @Override
    public List<CruCentroRecreativo> findAll() {
        return (List<CruCentroRecreativo>) repository.findAll();
    }
}
