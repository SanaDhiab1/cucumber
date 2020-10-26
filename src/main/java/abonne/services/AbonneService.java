package abonne.services;

import abonne.dto.Adresse;
import org.springframework.stereotype.Service;

@Service()
public class AbonneService {

    private ContratService contratService;

    private MouvementService mouvementService;

    public AbonneService(ContratService contratService, MouvementService mouvementService) {
        this.contratService = contratService;
        this.mouvementService = mouvementService;
    }

    public void updateMainAddress(Long idAbonne, Adresse nouvelleAdresse) {

        contratService.updateMainAddress(idAbonne, nouvelleAdresse);
        mouvementService.declencherMouvementChangementAdresse(idAbonne, nouvelleAdresse);
    }
}
