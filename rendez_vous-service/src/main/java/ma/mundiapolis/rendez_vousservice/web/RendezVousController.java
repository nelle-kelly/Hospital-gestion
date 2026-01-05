package ma.mundiapolis.rendez_vousservice.web;

import ma.mundiapolis.rendez_vousservice.dto.RendezVousReqDTO;
import ma.mundiapolis.rendez_vousservice.dto.RendezVousResDTO;
import ma.mundiapolis.rendez_vousservice.entities.StatutRendezVous;
import ma.mundiapolis.rendez_vousservice.mappers.RendezVousMapper;
import ma.mundiapolis.rendez_vousservice.services.RendezVousService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    final RendezVousService rendezVousService;
    final RendezVousMapper rendezVousMapper;

    public RendezVousController(RendezVousService rendezVousService, RendezVousMapper rendezVousMapper) {
        this.rendezVousService = rendezVousService;
        this.rendezVousMapper = rendezVousMapper;
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public ResponseEntity<RendezVousResDTO> create(
            @RequestBody RendezVousReqDTO dto) {

        RendezVousResDTO response = rendezVousService.createRendezVous(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ================= READ =================
    @GetMapping("/list")
    public ResponseEntity<List<RendezVousResDTO>> getAll() {
        return ResponseEntity.ok(rendezVousService.getAll());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVousResDTO>> getByPatient(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                rendezVousService.getByPatient(patientId)
        );
    }

    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<List<RendezVousResDTO>> getByMedecin(
            @PathVariable Long medecinId) {

        return ResponseEntity.ok(
                rendezVousService.getByMedecin(medecinId)
        );
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<RendezVousResDTO> update(
            @PathVariable Long id,
            @RequestBody RendezVousReqDTO dto) {

        return ResponseEntity.ok(
                rendezVousService.update(id, dto)
        );
    }

    // ================= UPDATE STATUT =================
    @PatchMapping("/{id}/statut")
    public ResponseEntity<RendezVousResDTO> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutRendezVous statut) {

        return ResponseEntity.ok(
                rendezVousService.updateStatut(id, statut)
        );
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rendezVousService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
