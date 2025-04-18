package it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi;

import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.Dipendente;
import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.DipendenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

@Service
@Validated
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Viaggio createViaggio(@Valid ViaggioRequest viaggioRequest) {
        Viaggio viaggio = new Viaggio();
        BeanUtils.copyProperties(viaggioRequest, viaggio);

        Dipendente dipendente = dipendenteRepository.findById(viaggioRequest.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        viaggio.setDipendente(dipendente);

        return viaggioRepository.save(viaggio);
    }

    public Viaggio updateViaggio(Long id, @Valid ViaggioRequest viaggioRequest) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        BeanUtils.copyProperties(viaggioRequest, viaggio);

        Dipendente dipendente = dipendenteRepository.findById(viaggioRequest.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        viaggio.setDipendente(dipendente);

        return viaggioRepository.save(viaggio);
    }

    public void deleteViaggio(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        viaggioRepository.delete(viaggio);
    }

    public ViaggioResponse getViaggio(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        ViaggioResponse response = new ViaggioResponse();
        BeanUtils.copyProperties(viaggio, response);
        response.setDataRichiesta(viaggio.getDataRichiesta().toString());
        response.setDipendenteId(viaggio.getDipendente().getId());

        return response;
    }

    public Page<Viaggio> getAllViaggi(Pageable pageable) {
        return viaggioRepository.findAll(pageable);
    }
}