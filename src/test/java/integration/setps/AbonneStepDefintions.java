package integration.setps;

import abonne.dto.Adresse;
import abonne.dto.Contrat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import integration.config.HttpRequestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AbonneStepDefintions extends HttpRequestManager{

    private Adresse adressePrincipale;

    @Given("^un abonné avec une adresse principale (.*) en France$")
    public void abonne_avec_adresse_principale_avec_statut(String statut) {
        adressePrincipale = getAdresseDeTest();
        adressePrincipale.setActive(statusToBoolean(statut));
    }

    @When("^le conseiller connecté à (.*) modifie l'adresse de l'abonné$")
    public void conseiller_modifie_adresse_sur_le_canal(String canal) throws IOException {
        String url = "/abonnes/12/adresse-principale";
        executePostQuery(url, toJsonString(adressePrincipale));
    }

    @Then("^l'adresse de l'abonné modifiée est enregistrée sur l'ensemble des contrats" +
            " de l'abonné Et un mouvement de modification d'adresse est créé$")
    public void adresse_bien_modifiee() throws IOException {

        assertServerResponseOk();

        String url = "/abonnes/12/contrats";
        executeGetRequest(url, getExpectedContracts());
        String returnedContractsAsString = EntityUtils.toString(getCurrentHttpResponse().
                getEntity());
        assertTrue(returnedContractsAsString.contains(toJsonString(getAdresseDeTest())));
    }

    private void assertServerResponseOk() {
        HttpResponse response = getCurrentHttpResponse();
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    public static String toJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private String getExpectedContracts() throws JsonProcessingException {
        Contrat contrat = new Contrat();
        contrat.setAdressePrincipale(getAdresseDeTest());
        contrat.setIdAbonne(12L);
        return toJsonString(List.of(contrat));
    }

    private Adresse getAdresseDeTest(){
        Adresse adresse = new Adresse();
        adresse.setAvenue("exemple");
        adresse.setCodePostale("94400");
        adresse.setActive(true);
        return adresse;
    }

    private boolean statusToBoolean(String statut) {
        if(statut.equalsIgnoreCase("active")){
            return true;
        }
        return false;
    }
}
