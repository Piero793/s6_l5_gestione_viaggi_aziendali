package it.epicode.s6_l5_gestione_viaggi_aziendali.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
