package it.epicode.s6_l5_gestione_viaggi_aziendali.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;

}
