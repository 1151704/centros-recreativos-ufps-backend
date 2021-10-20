package ufps.centrosrecreativos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufps.centrosrecreativos.api.container.ComunasApi;
import ufps.centrosrecreativos.api.service.CruComunaService;

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


}
