package ma.mundiapolis.rendez_vousservice.services;

import ma.mundiapolis.rendez_vousservice.dto.RendezVousReqDTO;
import ma.mundiapolis.rendez_vousservice.dto.RendezVousResDTO;
import ma.mundiapolis.rendez_vousservice.entities.StatutRendezVous;

import java.util.List;

public interface RendezVousService {
    RendezVousResDTO createRendezVous(RendezVousReqDTO rendezVousReqDTO);
    List<RendezVousResDTO> getAll();
    List<RendezVousResDTO> getByPatient(Long patientId);
    List<RendezVousResDTO> getByMedecin(Long medecinId);
    RendezVousResDTO update(Long id, RendezVousReqDTO rendezVousReqDTO);
    void delete(Long id);
    RendezVousResDTO updateStatut(Long id, StatutRendezVous statut);
}
