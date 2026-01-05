package ma.mundiapolis.rendez_vousservice.repositories;

import ma.mundiapolis.rendez_vousservice.entities.Rendez_vous;
import ma.mundiapolis.rendez_vousservice.entities.StatutRendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RendezVousRepo extends JpaRepository<Rendez_vous, Long> {

    List<Rendez_vous> findByPatientId(Long patientId);

    List<Rendez_vous> findByMedecinId(Long medecinId);

    List<Rendez_vous> findByDate(LocalDate date);

    List<Rendez_vous> findByStatut(StatutRendezVous statut);

}
