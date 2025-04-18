package it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteRequest {

    @NotBlank(message = "Lo username non può essere vuoto")
    @Size(max = 50, message = "Lo username deve avere massimo 50 caratteri")
    private String username;

    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(max = 50, message = "Il nome deve avere massimo 50 caratteri")
    private String nome;

    @NotBlank(message = "Il cognome non può essere vuoto")
    @Size(max = 50, message = "Il cognome deve avere massimo 50 caratteri")
    private String cognome;

    @NotBlank(message = "L'email non può essere vuota")
    @Email(message = "L'email deve essere valida")
    @Size(max = 50, message = "L'email deve avere massimo 50 caratteri")
    private String email;
}
