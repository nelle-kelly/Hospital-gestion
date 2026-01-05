package ma.mundiapolis.medecinservice.services;

import ma.mundiapolis.medecinservice.dto.MedecinReqDTO;
import ma.mundiapolis.medecinservice.dto.MedecinResDTO;
import ma.mundiapolis.medecinservice.entities.Medecin;
import ma.mundiapolis.medecinservice.mappers.MedecinMapper;
import ma.mundiapolis.medecinservice.repositories.MedecinRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinServiceImp implements MedecinService {
    final MedecinRepo medecinRepository;
    final MedecinMapper medecinMapper;

    public MedecinServiceImp(MedecinRepo medecinRepository, MedecinMapper medecinMapper) {
        this.medecinRepository = medecinRepository;
        this.medecinMapper = medecinMapper;
    }

    @Override
    public MedecinResDTO createMedecin(MedecinReqDTO medecinReqDTO) {
        Medecin medecin = medecinMapper.toEntity(medecinReqDTO);
        Medecin savedMedecin = medecinRepository.save(medecin);
        return medecinMapper.toDTO(savedMedecin);
    }

    @Override
    public MedecinResDTO getMedecinById(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé avec l'id: " + id));
        return medecinMapper.toDTO(medecin);
    }

    @Override
    public List<MedecinResDTO> getAllMedecins() {
        List<Medecin> medecins = medecinRepository.findAll();
        return medecinMapper.toDTOList(medecins);
    }

    @Override
    public MedecinResDTO updateMedecin(Long id, MedecinReqDTO medecinReqDTO) {
        Medecin existingMedecin = medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé avec l'id: " + id));

        medecinMapper.updateEntityFromDTO(medecinReqDTO, existingMedecin);
        Medecin updatedMedecin = medecinRepository.save(existingMedecin);
        return medecinMapper.toDTO(updatedMedecin);
    }

    @Override
    public void deleteMedecin(Long id) {
        if (!medecinRepository.existsById(id)) {
            throw new RuntimeException("Médecin non trouvé avec l'id: " + id);
        }
        medecinRepository.deleteById(id);
    }

    @Override
    public List<MedecinResDTO> searchMedecinsBySpecialite(String specialite) {
        List<Medecin> medecins = medecinRepository.findBySpecialiteContainingIgnoreCase(specialite);
        return medecinMapper.toDTOList(medecins);
    }
}
