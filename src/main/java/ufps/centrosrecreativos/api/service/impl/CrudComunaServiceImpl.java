package ufps.centrosrecreativos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.centrosrecreativos.api.model.CruComuna;
import ufps.centrosrecreativos.api.repository.CruComunaRepository;
import ufps.centrosrecreativos.api.service.CruComunaService;

import java.util.List;

@Service
public class CrudComunaServiceImpl implements CruComunaService {

    @Autowired
    private CruComunaRepository repository;

    @Override
    public List<CruComuna> findAll() {
        return (List<CruComuna>) repository.findAll();
    }
}
