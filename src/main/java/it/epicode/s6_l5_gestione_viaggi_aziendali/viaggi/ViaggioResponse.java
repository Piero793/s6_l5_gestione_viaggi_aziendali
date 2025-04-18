package it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioResponse {
    private Long id;
    private String destinazione;
    private String dataRichiesta;
    private StatoViaggio statoViaggio;
    private Long dipendenteId;
}
