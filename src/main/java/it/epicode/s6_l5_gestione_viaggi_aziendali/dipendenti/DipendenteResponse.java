package it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteResponse {
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String fotoUrl;
}
