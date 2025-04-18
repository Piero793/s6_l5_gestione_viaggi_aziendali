package it.epicode.s6_l5_gestione_viaggi_aziendali.prenotazioni;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndDataPrenotazione(Long dipendenteId, LocalDate dataPrenotazione);
}
