package ma.mundiapolis.medecinservice.repositories;

import ma.mundiapolis.medecinservice.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepo extends JpaRepository<Medecin, Long> {
    List<Medecin> findBySpecialiteContainingIgnoreCase(String specialite);

    List<Medecin> findByNomContainingIgnoreCase(String nom);
}
