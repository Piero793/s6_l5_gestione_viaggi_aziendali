package it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi;

import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.Dipendente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 50, nullable = false)
    private String destinazione;
    @Column(nullable = false)
    private LocalDate dataRichiesta;
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;
    @ManyToOne
    private Dipendente dipendente;
}
