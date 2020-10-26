package abonne.controller;

import abonne.dto.Adresse;
import abonne.dto.Contrat;
import abonne.services.AbonneService;
import abonne.services.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AbonneController {

    @Autowired
    private AbonneService abonneService;

    @Autowired
    private ContratService contratService;

    @PostMapping("/abonnes/{id}/adresse-principale")
    public void updateAdressePrincipale(@PathVariable("id") Long idAbonne,
                                        @RequestBody Adresse adresse) {
        abonneService.updateMainAddress(idAbonne, adresse);
    }

    @GetMapping("/abonnes/{id}/contrats")
    public ResponseEntity<List<Contrat>> getContracts(@PathVariable("id") Long idAbonne) {

        return new ResponseEntity<>(
                contratService.getContractsBySubscriberId(idAbonne), HttpStatus.OK);
    }
}
