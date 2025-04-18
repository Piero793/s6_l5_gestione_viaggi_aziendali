package it.epicode.s6_l5_gestione_viaggi_aziendali.viaggi;

import it.epicode.s6_l5_gestione_viaggi_aziendali.common.CommonResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio createViaggio(@RequestBody @Valid ViaggioRequest viaggioRequest) {
        return viaggioService.createViaggio(viaggioRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio updateViaggio(@PathVariable Long id, @RequestBody @Valid ViaggioRequest viaggioRequest) {
        return viaggioService.updateViaggio(id, viaggioRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteViaggio(id);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ViaggioResponse getViaggio(@PathVariable Long id) {
        return viaggioService.getViaggio(id);
    }

    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public Page<Viaggio> getAllViaggi(@ParameterObject Pageable pageable) {
        return viaggioService.getAllViaggi(pageable);
    }

    @PutMapping("/update-stato/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateStatoViaggio(@PathVariable Long id, @RequestParam StatoViaggio nuovoStato) {
        ViaggioResponse viaggioAggiornato = viaggioService.updateStatoViaggio(id, nuovoStato);
        return ResponseEntity.ok("Stato viaggio aggiornato con successo: " + viaggioAggiornato);
    }
}
