package ma.mundiapolis.patientservice.services;

import jakarta.transaction.Transactional;
import ma.mundiapolis.patientservice.dto.PatientReqDTO;
import ma.mundiapolis.patientservice.dto.PatientResDTO;
import ma.mundiapolis.patientservice.entities.Patient;
import ma.mundiapolis.patientservice.mappers.PatientMapper;
import ma.mundiapolis.patientservice.repositories.PatientREPO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService {

    final PatientREPO patientRepository;
    final PatientMapper patientMapper;

    public PatientServiceImp(PatientREPO patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientResDTO createPatient(PatientReqDTO patientReqDTO) {
        Patient patient = patientMapper.toEntity(patientReqDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }


   @Override
    public PatientResDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + id));
        return patientMapper.toDTO(patient);
    }


    @Override
    public List<PatientResDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toDTOList(patients);
    }

    @Override
    public PatientResDTO updatePatient(Long id, PatientReqDTO patientReqDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + id));

        patientMapper.updateEntityFromDTO(patientReqDTO, existingPatient);
        Patient updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
       // log.info("Deleting patient with id: {}", id);
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient non trouvé avec l'id: " + id);
        }
        patientRepository.deleteById(id);
    }

   // @Transactional(readOnly = true)
   @Override
    public List<PatientResDTO> searchPatientsByNom(String nom) {
        List<Patient> patients = patientRepository.findByNomContainingIgnoreCase(nom);
        return patientMapper.toDTOList(patients);
    }
}
