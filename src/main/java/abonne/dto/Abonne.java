package abonne.dto;

import java.util.Objects;

public class Abonne {

    private Long id;
    
    private Adresse adressePrincipale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Abonne abonne = (Abonne) o;
        return Objects.equals(id, abonne.id) &&
                Objects.equals(adressePrincipale, abonne.adressePrincipale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adressePrincipale);
    }
}
