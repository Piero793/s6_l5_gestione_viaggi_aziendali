package it.epicode.s6_l5_gestione_viaggi_aziendali.prenotazioni;

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
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponse createPrenotazione(@RequestBody @Valid PrenotazioneRequest prenotazioneRequest) {
        return prenotazioneService.createPrenotazione(prenotazioneRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrenotazioneResponse updatePrenotazione(@PathVariable Long id, @RequestBody @Valid PrenotazioneRequest prenotazioneRequest) {
        return prenotazioneService.updatePrenotazione(id, prenotazioneRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrenotazioneResponse getPrenotazione(@PathVariable Long id) {
        return prenotazioneService.getPrenotazione(id);
    }

    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public Page<Prenotazione> getAllPrenotazioni(@ParameterObject Pageable pageable) {
        return prenotazioneService.getAllPrenotazioni(pageable);
    }
}