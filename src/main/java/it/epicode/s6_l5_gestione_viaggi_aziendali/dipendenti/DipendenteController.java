package it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti;

import it.epicode.s6_l5_gestione_viaggi_aziendali.common.CommonResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente createDipendente(@RequestBody @Valid DipendenteRequest dipendenteRequest) {
        return dipendenteService.createDipendente(dipendenteRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateDipendente(@PathVariable Long id, @RequestBody @Valid DipendenteRequest dipendenteRequest) {
        Dipendente dipendenteAggiornato = dipendenteService.updateDipendente(id, dipendenteRequest);
        return new CommonResponse(dipendenteAggiornato.getId());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DipendenteResponse getDipendente(@PathVariable Long id) {
        Dipendente dipendente = dipendenteService.getDipendente(id);
        return new DipendenteResponse(dipendente.getId(), dipendente.getUsername(), dipendente.getNome(), dipendente.getCognome(), dipendente.getEmail());
    }

    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public Page<DipendenteResponse> getAllDipendenti(@ParameterObject Pageable pageable) {
        Page<Dipendente> dipendenti = dipendenteService.getAllDipendenti(pageable);
        return dipendenti.map(d -> new DipendenteResponse(d.getId(), d.getUsername(), d.getNome(), d.getCognome(), d.getEmail()));
    }
}
