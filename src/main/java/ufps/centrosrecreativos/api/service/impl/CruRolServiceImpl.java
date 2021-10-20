package ufps.centrosrecreativos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.centrosrecreativos.api.model.CruRol;
import ufps.centrosrecreativos.api.repository.CruRolRepository;
import ufps.centrosrecreativos.api.service.CruRolService;

@Service
public class CruRolServiceImpl implements CruRolService {

	@Autowired
	private CruRolRepository repository;

	@Override
	public CruRol findByRol(String rol) {
		try {
			return repository.findByRol(rol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
