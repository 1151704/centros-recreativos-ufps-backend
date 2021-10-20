package ufps.centrosrecreativos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufps.centrosrecreativos.api.container.CentrosRecreativosApi;
import ufps.centrosrecreativos.api.service.CruCentroRecreativoService;

@RestController
@RequestMapping("/api/centro-recreativo/")
public class CentroRecreativoController {

    @Autowired
    private CruCentroRecreativoService service;

    @GetMapping("all")
    public CentrosRecreativosApi getAllCentrosRecreativos() {
        CentrosRecreativosApi centros = new CentrosRecreativosApi();

        try {
            centros.setCentros(service.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return centros;
    }

}
