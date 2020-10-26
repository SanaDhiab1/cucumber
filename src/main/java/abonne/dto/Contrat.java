package abonne.dto;

import java.util.Objects;

public class Contrat {

    Long idAbonne;

    Adresse adressePrincipale;
    
    // and other informations here

    public Long getIdAbonne() {
        return idAbonne;
    }

    public void setIdAbonne(Long idAbonne) {
        this.idAbonne = idAbonne;
    }

    public Adresse getAdressePrincipale() {
        return adressePrincipale;
    }

    public void setAdressePrincipale(Adresse adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrat contrat = (Contrat) o;
        return Objects.equals(idAbonne, contrat.idAbonne) &&
                Objects.equals(adressePrincipale, contrat.adressePrincipale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAbonne, adressePrincipale);
    }
}
