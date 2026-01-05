package ma.mundiapolis.medecinservice.web;


import ma.mundiapolis.medecinservice.dto.MedecinReqDTO;
import ma.mundiapolis.medecinservice.dto.MedecinResDTO;
import ma.mundiapolis.medecinservice.mappers.MedecinMapper;
import ma.mundiapolis.medecinservice.services.MedecinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecin")
public class MedecinController {

    final MedecinService medecinService;
    final MedecinMapper medecinMapper;

    public MedecinController(MedecinService medecinService, MedecinMapper medecinMapper) {
        this.medecinService = medecinService;
        this.medecinMapper = medecinMapper;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MedecinResDTO createMedecin(@RequestBody MedecinReqDTO medecinReqDTO) {
        System.out.println(medecinReqDTO);
        return medecinService.createMedecin(medecinReqDTO);

    }

    @GetMapping("/{id}")
    public MedecinResDTO getMedecinById(@PathVariable Long id) {
        return medecinService.getMedecinById(id);

    }

    @GetMapping("/patients")
    public List<MedecinResDTO> getAllMedecins() {
        return medecinService.getAllMedecins();

    }

    @PutMapping("/update/{id}")
    public MedecinResDTO updateMedecin(
            @PathVariable Long id,
            @RequestBody MedecinReqDTO medecinReqDTO) {
        return medecinService.updateMedecin(id, medecinReqDTO);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<MedecinResDTO> searchMedecins(@RequestParam String specialite) {
        return medecinService.searchMedecinsBySpecialite(specialite);

    }
}
