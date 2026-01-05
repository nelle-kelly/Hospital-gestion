package ma.mundiapolis.medecinservice.services;

import ma.mundiapolis.medecinservice.dto.MedecinReqDTO;
import ma.mundiapolis.medecinservice.dto.MedecinResDTO;

import java.util.List;

public interface MedecinService {
    MedecinResDTO createMedecin(MedecinReqDTO medecinReqDTO);
    MedecinResDTO getMedecinById(Long id);
    List<MedecinResDTO> getAllMedecins();
    MedecinResDTO updateMedecin(Long id, MedecinReqDTO medecinReqDTO);
    void deleteMedecin(Long id);
    List<MedecinResDTO> searchMedecinsBySpecialite(String specialite);
}
