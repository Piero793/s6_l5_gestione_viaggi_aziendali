package it.epicode.s6_l5_gestione_viaggi_aziendali.prenotazioni;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneResponse {

    private Long id;
    private LocalDate dataPrenotazione;
    private StatoPrenotazione statoPrenotazione;
    private Long dipendenteId;
    private Long viaggioId;
}
