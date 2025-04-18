package it.epicode.s6_l5_gestione_viaggi_aziendali.prenotazioni;

import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.Dipendente;
import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.DipendenteRepository;
import it.epicode.s6_l5_gestione_viaggi_aziendali.exceptions.ErrorHandler;
import it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi.Viaggio;
import it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    //  Creazione di una prenotazione con gestione dell'eccezione personalizzata
    public PrenotazioneResponse createPrenotazione(@Valid PrenotazioneRequest prenotazioneRequest) {
        boolean esistePrenotazione = prenotazioneRepository.existsByDipendenteIdAndDataPrenotazione(
                prenotazioneRequest.getDipendenteId(), prenotazioneRequest.getDataPrenotazione());

        if (esistePrenotazione) {
            throw new ErrorHandler.PrenotazioneDuplicataException("Il dipendente ha già una prenotazione per questa data");
        }

        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(prenotazioneRequest, prenotazione);

        Dipendente dipendente = dipendenteRepository.findById(prenotazioneRequest.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        prenotazione.setDipendente(dipendente);

        Viaggio viaggio = viaggioRepository.findById(prenotazioneRequest.getViaggioId())
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        prenotazione.setViaggio(viaggio);

        prenotazioneRepository.save(prenotazione);

        return new PrenotazioneResponse(
                prenotazione.getId(),
                prenotazione.getDataPrenotazione(),
                prenotazione.getStatoPrenotazione(),
                prenotazione.getDipendente().getId(),
                prenotazione.getViaggio().getId()
        );
    }

    //  Aggiornamento di una prenotazione
    public PrenotazioneResponse updatePrenotazione(Long id, @Valid PrenotazioneRequest prenotazioneRequest) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        //  Se la data è cambiata, verifica se esiste già un'altra prenotazione
        if (!prenotazione.getDataPrenotazione().equals(prenotazioneRequest.getDataPrenotazione())) {
            boolean esistePrenotazione = prenotazioneRepository.existsByDipendenteIdAndDataPrenotazione(
                    prenotazioneRequest.getDipendenteId(), prenotazioneRequest.getDataPrenotazione());

            if (esistePrenotazione) {
                throw new ErrorHandler.PrenotazioneDuplicataException("Il dipendente ha già una prenotazione per questa data");
            }
        }

        BeanUtils.copyProperties(prenotazioneRequest, prenotazione);

        Dipendente dipendente = dipendenteRepository.findById(prenotazioneRequest.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        prenotazione.setDipendente(dipendente);

        Viaggio viaggio = viaggioRepository.findById(prenotazioneRequest.getViaggioId())
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        prenotazione.setViaggio(viaggio);

        prenotazioneRepository.save(prenotazione);

        return new PrenotazioneResponse(
                prenotazione.getId(),
                prenotazione.getDataPrenotazione(),
                prenotazione.getStatoPrenotazione(),
                prenotazione.getDipendente().getId(),
                prenotazione.getViaggio().getId()
        );
    }

    //  Eliminazione di una prenotazione
    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        prenotazioneRepository.delete(prenotazione);
    }

    //  Recupero di una prenotazione specifica
    public PrenotazioneResponse getPrenotazione(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        return new PrenotazioneResponse(
                prenotazione.getId(),
                prenotazione.getDataPrenotazione(),
                prenotazione.getStatoPrenotazione(),
                prenotazione.getDipendente().getId(),
                prenotazione.getViaggio().getId()
        );
    }

    //  Recupero di tutte le prenotazioni
    public Page<Prenotazione> getAllPrenotazioni(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable);
    }
}