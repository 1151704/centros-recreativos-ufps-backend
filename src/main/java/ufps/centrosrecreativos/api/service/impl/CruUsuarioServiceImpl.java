package ufps.centrosrecreativos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.centrosrecreativos.api.model.CruRol;
import ufps.centrosrecreativos.api.model.CruUsuario;
import ufps.centrosrecreativos.api.repository.CruUsuarioRepository;
import ufps.centrosrecreativos.api.service.CruUsuarioService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CruUsuarioServiceImpl implements CruUsuarioService {

	@Autowired
	private CruUsuarioRepository repository;

	@Override
	public List<CruUsuario> findAll() {
		try {
			return (ArrayList<CruUsuario>) repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CruUsuario> findByRol(CruRol rol) {
		try {
			return repository.findByRol(rol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CruUsuario save(CruUsuario entity) {
		try {

			entity.setApellidos(entity.getApellidos().toUpperCase());
			entity.setNombres(entity.getNombres().toUpperCase());
			entity.setUsername(entity.getUsername().toUpperCase());

			return repository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CruUsuario findByUsernameAndIdentificacion(String username, String identificacion) {
		try {
			return repository.findByUsernameIgnoreCaseAndIdentificacion(username, identificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CruUsuario findByUsername(String username) {
		try {
			return repository.findByUsernameIgnoreCase(username);
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public CruUsuario findByEmail(String email) {
		try {
			return repository.findByEmail(email);
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public CruUsuario findByIdentificacion(String identificacion) {
		try {
			return repository.findByIdentificacion(identificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean existByUsernameAndIdentificacion(String username, String identificacion) {
		try {
			return repository.existsByUsernameIgnoreCaseAndIdentificacion(username, identificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
