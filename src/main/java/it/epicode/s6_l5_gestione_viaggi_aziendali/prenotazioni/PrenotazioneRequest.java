package it.epicode.s6_l5_gestione_viaggi_aziendali.prenotazioni;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import jakarta.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {

    @NotNull(message = "La data di prenotazione è obbligatoria")
    @PastOrPresent(message = "La data di prenotazione non può essere nel futuro")
    private LocalDate dataPrenotazione;

    @NotNull(message = "Lo stato della prenotazione è obbligatorio")
    private StatoPrenotazione statoPrenotazione;

    @NotNull(message = "L'ID del dipendente è obbligatorio")
    private Long dipendenteId;

    @NotNull(message = "L'ID del viaggio è obbligatorio")
    private Long viaggioId;

    @Size(max = 255, message = "Le note non possono superare 255 caratteri")
    private String note;
}