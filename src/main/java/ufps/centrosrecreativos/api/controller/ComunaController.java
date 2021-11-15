package ufps.centrosrecreativos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufps.centrosrecreativos.api.container.*;
import ufps.centrosrecreativos.api.model.CruComuna;
import ufps.centrosrecreativos.api.service.CruComunaService;
import ufps.centrosrecreativos.api.util.ValidationException;

@RestController
@RequestMapping("/api/comuna/")
public class ComunaController {

    @Autowired
    private CruComunaService service;

    @GetMapping("all")
    public ComunasApi getAllComunas() {
        ComunasApi comunas = new ComunasApi();

        try {
            comunas.setComunas(service.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comunas;
    }

    @GetMapping("{id}")
    public ComunaApi getComunaPorId(@PathVariable Integer id) {
        ComunaApi comunas = new ComunaApi();

        try {
            comunas.setComuna(service.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comunas;
    }

    @PostMapping("save")
    public ResponseEntity<ComunaApi> save(@RequestBody(required = false) ComunaEntradaApi entrada) {

        ComunaApi api = new ComunaApi();
        CruComuna centro;
        if (entrada.getId() != null) {
            centro = service.findById(entrada.getId());
        } else {
            centro = new CruComuna();
        }
        centro.setCodigoComuna(entrada.getCodigoComuna());
        centro.setNombreComuna(entrada.getNombreComuna());

        try {
            api.setComuna(service.save(centro));
        } catch (Exception e) {
            throw new ValidationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(api, HttpStatus.OK);
    }


}
