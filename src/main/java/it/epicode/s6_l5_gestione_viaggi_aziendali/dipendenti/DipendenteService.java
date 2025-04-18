package it.epicode.s6_l5_gestione_viaggi_aziendali.dipendenti;

import com.cloudinary.Cloudinary;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Validated
public class DipendenteService {
    @Autowired DipendenteRepository dipendenteRepository;
    @Autowired Cloudinary cloudinary;

    // creo un dipendente usando il dto RipendenteRequest
    public Dipendente createDipendente(@Valid DipendenteRequest dipendenteRequest) {
        Dipendente dipendente = new Dipendente();
        BeanUtils.copyProperties(dipendenteRequest, dipendente);
        return dipendenteRepository.save(dipendente);
    }

    // aggiorno un dipendente
    public Dipendente updateDipendente(Long id, @Valid DipendenteRequest dipendenteRequest) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        BeanUtils.copyProperties(dipendenteRequest, dipendente);
        return dipendenteRepository.save(dipendente);
    }

    // elimino un dipendente
    public void deleteDipendente(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        dipendenteRepository.delete(dipendente);
    }

    // trovo un dipendente
    public Dipendente getDipendente(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
    }

    // trovo tutti i dipendenti
    public Page<Dipendente> getAllDipendenti(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

    // metodo per aggiungere una foto
    public String uploadFoto(Long id, MultipartFile file) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        try {
            // Carica l'immagine su Cloudinary
            Map result = cloudinary.uploader()
                    .upload(file.getBytes(), Cloudinary.asMap("folder", "FS1024", "public_id", file.getOriginalFilename()));

            // Recupera l'URL dell'immagine e lo salva nel database
            String imageUrl = result.get("secure_url").toString();
            dipendente.setFotoUrl(imageUrl);
            dipendenteRepository.save(dipendente);

            return imageUrl;
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il caricamento su Cloudinary");
        }
    }

}
