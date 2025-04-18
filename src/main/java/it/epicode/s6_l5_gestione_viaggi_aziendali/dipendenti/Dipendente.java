package it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 50, nullable = false)
    private String nome;
    @Column(length = 50, nullable = false)
    private String cognome;
    @Column(length = 50, nullable = false)
    private String email;
}