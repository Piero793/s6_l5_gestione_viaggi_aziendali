package it.epicode.s6_l5_gestione_viaggi_aziendali.prenotazioni;

import it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti.Dipendente;
import it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi.Viaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataPrenotazione;

    @Enumerated(EnumType.STRING)
    private StatoPrenotazione statoPrenotazione;

    @ManyToOne
    private Dipendente dipendente;

    @ManyToOne
    private Viaggio viaggio;
}
