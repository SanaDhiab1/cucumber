package abonne.services;

import abonne.dto.Adresse;
import abonne.dto.Contrat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratService {

    public List<Contrat> getContractsBySubscriberId(Long subscriberId){
        return List.of(new Contrat());
    }

    public void updateMainAddress(Long idAbonne, Adresse adresse){
        // it should update the main address of all subscriber contracts here
    }
}
