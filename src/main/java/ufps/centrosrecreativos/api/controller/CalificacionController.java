package ufps.centrosrecreativos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufps.centrosrecreativos.api.container.CalificacionEntradaApi;
import ufps.centrosrecreativos.api.container.ComunaApi;
import ufps.centrosrecreativos.api.container.ComunaEntradaApi;
import ufps.centrosrecreativos.api.container.ComunasApi;
import ufps.centrosrecreativos.api.model.CruCalificacionCentro;
import ufps.centrosrecreativos.api.model.CruComuna;
import ufps.centrosrecreativos.api.repository.CruCalificacionCentroRepository;
import ufps.centrosrecreativos.api.service.CruCentroRecreativoService;
import ufps.centrosrecreativos.api.service.CruComunaService;
import ufps.centrosrecreativos.api.util.ValidationException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/calificacion/")
public class CalificacionController {

    @Autowired
    private CruCalificacionCentroRepository service;

    @Autowired
    private CruCentroRecreativoService centroService;

    @GetMapping("{id}")
    public List<CruCalificacionCentro> getComunaPorId(@PathVariable Integer id) {
        try {
            return service.findByCentroAndEnable(centroService.findById(id), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @PostMapping("save")
    public ResponseEntity<CruCalificacionCentro> save(@RequestBody(required = false) CalificacionEntradaApi entrada) {

        CruCalificacionCentro centro;
        if (entrada.getId() != null) {
            centro = service.findById(entrada.getId()).orElse(null);
        } else {
            centro = new CruCalificacionCentro();
            centro.setCentro(centroService.findById(entrada.getIdCentro()));
        }

        centro.setCalificacion(entrada.getCalificacion());
        centro.setCorreo(entrada.getCorreo());
        centro.setNombre(entrada.getNombre());
        centro.setObservacion(entrada.getObservacion());

        try {
            centro = service.save(centro);
        } catch (Exception e) {
            throw new ValidationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(centro, HttpStatus.OK);
    }


}
