package it.epicode.s6_l5_gestione_viaggi_aziendali.common;

import com.github.javafaker.Faker;
import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.Dipendente;
import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.DipendenteRequest;
import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.DipendenteService;
import it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi.StatoViaggio;
import it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi.ViaggioRequest;
import it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi.ViaggioService;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Component
public class CommonRunner implements CommandLineRunner {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private Faker faker;

    @Override
    public void run(String... args) throws Exception {
        // Creazione di 10 dipendenti
        for (int i = 0; i < 10; i++) {
            DipendenteRequest dipendenteRequest = new DipendenteRequest(
                    faker.name().username(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress()
            );
            dipendenteService.createDipendente(dipendenteRequest);
        }

        //  Recupera tutti i dipendenti dal database per associarli ai viaggi
        List<Dipendente> dipendenti = dipendenteService.getAllDipendenti(Pageable.unpaged()).getContent();

        //  Creazione di 10 viaggi
        for (int i = 0; i < 10; i++) {
            ViaggioRequest viaggioRequest = new ViaggioRequest(
                    faker.address().city(),
                    faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).toInstant()
                            .atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
                    StatoViaggio.values()[faker.random().nextInt(StatoViaggio.values().length)],
                    dipendenti.get(faker.random().nextInt(dipendenti.size())).getId()
            );
            viaggioService.createViaggio(viaggioRequest);
        }

    }
}