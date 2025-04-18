package it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}
