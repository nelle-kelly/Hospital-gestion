package ma.mundiapolis.patientservice.web;

import ma.mundiapolis.patientservice.dto.PatientReqDTO;
import ma.mundiapolis.patientservice.dto.PatientResDTO;
import ma.mundiapolis.patientservice.mappers.PatientMapper;
import ma.mundiapolis.patientservice.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    final PatientService patientService;
    final PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping(value = "/create", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResDTO createPatient(@RequestBody PatientReqDTO patientReqDTO) {
        System.out.println(patientReqDTO);
        return patientService.createPatient(patientReqDTO);

    }

    @GetMapping("/{id}")
    public PatientResDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);


    }

    @GetMapping("/patients")
    public List<PatientResDTO> getAllPatients() {
        return patientService.getAllPatients();

    }

    @PutMapping("/update/{id}")
    public PatientResDTO updatePatient(
            @PathVariable Long id,
            @RequestBody PatientReqDTO patientReqDTO) {
        return patientService.updatePatient(id, patientReqDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


}
