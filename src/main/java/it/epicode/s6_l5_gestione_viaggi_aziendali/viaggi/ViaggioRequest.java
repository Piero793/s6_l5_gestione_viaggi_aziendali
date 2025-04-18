package it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioRequest {

    @NotBlank(message = "La destinazione non può essere vuota")
    @Size(max = 50, message = "La destinazione deve avere massimo 50 caratteri")
    private String destinazione;

    @PastOrPresent(message = "La data richiesta non può essere nel futuro")
    private LocalDate dataRichiesta;

    @NotNull(message = "Lo stato del viaggio non può essere vuoto")
    private StatoViaggio statoViaggio;

    @NotNull(message = "L'id del dipendente non può essere vuoto")
    private Long dipendenteId;
}
