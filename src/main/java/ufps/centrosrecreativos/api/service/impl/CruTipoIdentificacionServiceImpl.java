package ufps.centrosrecreativos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.centrosrecreativos.api.model.CruTipoIdentificacion;
import ufps.centrosrecreativos.api.repository.CruTipoIdentificacionRepository;
import ufps.centrosrecreativos.api.service.CruTipoIdentificacionService;

@Service
public class CruTipoIdentificacionServiceImpl implements CruTipoIdentificacionService {

	@Autowired
	private CruTipoIdentificacionRepository repository;

	@Override
	public CruTipoIdentificacion findByTipo(String tipo) {
		try {
			return repository.findByTipo(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
