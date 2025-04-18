package it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti;

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
public class DipendenteService {
    @Autowired DipendenteRepository dipendenteRepository;

    // creo un dipendente usando il dto RipendenteRequest
    public Dipendente createDipendente(@Valid DipendenteRequest dipendenteRequest) {
        Dipendente dipendente = new Dipendente();
        BeanUtils.copyProperties(dipendenteRequest, dipendente);
        return dipendenteRepository.save(dipendente);
    }

    // aggiorno un dipendente usando il dto CommonResponse
    public Dipendente updateDipendente(Long id, @Valid DipendenteRequest dipendenteRequest) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        BeanUtils.copyProperties(dipendenteRequest, dipendente);
        return dipendenteRepository.save(dipendente);
    }

    // elimino un dipendente usando il dto CommonResponse
    public void deleteDipendente(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        dipendenteRepository.delete(dipendente);
    }

    // trovo un dipendente usando il dto CommonResponse
    public Dipendente getDipendente(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
    }

    // trovo tutti i dipendenti usando il dto CommonResponse
    public Page<Dipendente> getAllDipendenti(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

}
