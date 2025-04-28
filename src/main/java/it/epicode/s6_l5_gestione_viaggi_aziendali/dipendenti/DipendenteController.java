package it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti;

import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponse createDipendente(@RequestBody @Valid DipendenteRequest dipendenteRequest) {
        Dipendente dipendente = dipendenteService.createDipendente(dipendenteRequest);
        return new DipendenteResponse(dipendente.getId(), dipendente.getUsername(), dipendente.getNome(), dipendente.getCognome(), dipendente.getEmail(), dipendente.getFotoUrl());
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public DipendenteResponse updateDipendente(@PathVariable Long id, @RequestBody @Valid DipendenteRequest dipendenteRequest) {
        Dipendente dipendenteAggiornato = dipendenteService.updateDipendente(id, dipendenteRequest);
        return new DipendenteResponse(dipendenteAggiornato.getId(), dipendenteAggiornato.getUsername(), dipendenteAggiornato.getNome(), dipendenteAggiornato.getCognome(), dipendenteAggiornato.getEmail(), dipendenteAggiornato.getFotoUrl());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public DipendenteResponse getDipendente(@PathVariable Long id) {
        Dipendente dipendente = dipendenteService.getDipendente(id);
        return new DipendenteResponse(dipendente.getId(), dipendente.getUsername(), dipendente.getNome(), dipendente.getCognome(), dipendente.getEmail(), dipendente.getFotoUrl());
    }

    @GetMapping("/getall")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Page<DipendenteResponse> getAllDipendenti(@ParameterObject Pageable pageable) {
        Page<Dipendente> dipendenti = dipendenteService.getAllDipendenti(pageable);
        return dipendenti.map(d -> new DipendenteResponse(d.getId(), d.getUsername(), d.getNome(), d.getCognome(), d.getEmail(), d.getFotoUrl()));
    }

    // qui aggiungo un'immagine
    @PostMapping(value = "/upload-foto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> uploadFoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String imageUrl = dipendenteService.uploadFoto(id, file);
        return ResponseEntity.ok("Foto caricata con successo! URL: " + imageUrl);
    }
}