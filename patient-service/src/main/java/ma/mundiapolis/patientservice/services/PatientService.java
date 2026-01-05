package ma.mundiapolis.patientservice.services;

import ma.mundiapolis.patientservice.dto.PatientReqDTO;
import ma.mundiapolis.patientservice.dto.PatientResDTO;

import java.util.List;

public interface PatientService {
    PatientResDTO getPatientById(Long id);
    PatientResDTO createPatient(PatientReqDTO patientReqDTO);
    List<PatientResDTO> getAllPatients();
    PatientResDTO updatePatient(Long id, PatientReqDTO patientReqDTO);
    void deletePatient(Long id);
    List<PatientResDTO> searchPatientsByNom(String nom);
}
