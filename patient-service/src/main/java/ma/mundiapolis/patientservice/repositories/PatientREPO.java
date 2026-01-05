package ma.mundiapolis.patientservice.repositories;

import ma.mundiapolis.patientservice.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientREPO extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContainingIgnoreCase(String nom);


}
