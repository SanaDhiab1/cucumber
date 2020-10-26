package abonne.dto;

import java.util.Objects;

public class Adresse {

    private String avenue;
    
    private String ville;
    
    private String codePostale;
    
    private boolean active;

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return active == adresse.active &&
                Objects.equals(avenue, adresse.avenue) &&
                Objects.equals(ville, adresse.ville) &&
                Objects.equals(codePostale, adresse.codePostale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avenue, ville, codePostale, active);
    }
}
