package ma.mundiapolis.rendez_vousservice.services;

import jakarta.transaction.Transactional;
import ma.mundiapolis.rendez_vousservice.dto.MedecinDTO;
import ma.mundiapolis.rendez_vousservice.dto.PatientDTO;
import ma.mundiapolis.rendez_vousservice.dto.RendezVousReqDTO;
import ma.mundiapolis.rendez_vousservice.dto.RendezVousResDTO;
import ma.mundiapolis.rendez_vousservice.entities.Rendez_vous;
import ma.mundiapolis.rendez_vousservice.entities.StatutRendezVous;
import ma.mundiapolis.rendez_vousservice.mappers.RendezVousMapper;
import ma.mundiapolis.rendez_vousservice.repositories.RendezVousRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class RendezVousServiceImp implements RendezVousService {

    private final RendezVousRepo rendezVousRepository;
    private final RendezVousMapper rendezVousMapper;
    private final  WebClient webClient;

    private static final String PATIENT_SERVICE_URL = "http://PATIENT-SERVICE/api/patient/";
    private static final String MEDECIN_SERVICE_URL = "http://MEDECIN-SERVICE/api/medecin/";

    public RendezVousServiceImp(RendezVousRepo rendezVousRepository, RendezVousMapper rendezVousMapper, WebClient webClient) {
        this.rendezVousRepository = rendezVousRepository;
        this.rendezVousMapper = rendezVousMapper;
        this.webClient = webClient;
    }


    private RendezVousResDTO enrich(Rendez_vous rv) {

        RendezVousResDTO dto = rendezVousMapper.toDTO(rv);

        // Patient
        PatientDTO patient = webClient.get()
                .uri(PATIENT_SERVICE_URL + rv.getPatientId())
                .retrieve()
                .bodyToMono(PatientDTO.class)
                .block();

        // Medecin
        MedecinDTO medecin = webClient.get()
                .uri(MEDECIN_SERVICE_URL + rv.getMedecinId())
                .retrieve()
                .bodyToMono(MedecinDTO.class)
                .block();

        dto.setPatient(patient);
        dto.setMedecin(medecin);

        return dto;
    }


    // ================= CREATE =================
    @Override
    public RendezVousResDTO createRendezVous(RendezVousReqDTO rendezVousReqDTO) {

        // Vérifier patient
        PatientDTO patient = webClient.get()
                .uri(PATIENT_SERVICE_URL + rendezVousReqDTO.getPatientId())
                .retrieve()
                .bodyToMono(PatientDTO.class)
                .block();

        if (patient == null) {
            throw new RuntimeException("Patient non trouvé : " + rendezVousReqDTO.getPatientId());
        }

        // Vérifier médecin
        MedecinDTO medecin = webClient.get()
                .uri(MEDECIN_SERVICE_URL + rendezVousReqDTO.getMedecinId())
                .retrieve()
                .bodyToMono(MedecinDTO.class)
                .block();

        if (medecin == null) {
            throw new RuntimeException("Médecin non trouvé : " + rendezVousReqDTO.getMedecinId());
        }



        Rendez_vous rendezVous = rendezVousMapper.toEntity(rendezVousReqDTO);
        rendezVous.setStatut(StatutRendezVous.PLANIFIE);

        Rendez_vous saved = rendezVousRepository.save(rendezVous);

        return enrich(saved);


    }

    // ================= READ =================
    @Override
    public List<RendezVousResDTO> getAll() {
        return rendezVousRepository.findAll()
                .stream()
                .map(this::enrich)
                .toList();
    }

    @Override
    public List<RendezVousResDTO> getByPatient(Long patientId) {
        return rendezVousRepository.findByPatientId(patientId)
                .stream()
                .map(this::enrich)
                .toList();
    }

    @Override
    public List<RendezVousResDTO> getByMedecin(Long medecinId) {
        return rendezVousRepository.findByMedecinId(medecinId)
                .stream()
                .map(this::enrich)
                .toList();
    }

    // ================= UPDATE =================
    @Override
    public RendezVousResDTO update(Long id, RendezVousReqDTO rendezVousReqDTO) {
        Rendez_vous rv = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable"));

        rendezVousMapper.updateEntityFromDTO(rendezVousReqDTO, rv);
        return rendezVousMapper.toDTO(rendezVousRepository.save(rv));
    }

    // ================= DELETE =================
    @Override
    public void delete(Long id) {
        if (!rendezVousRepository.existsById(id)) {
            throw new RuntimeException("Rendez-vous introuvable");
        }
        rendezVousRepository.deleteById(id);
    }

    // ================= STATUT =================
    @Override
    public RendezVousResDTO updateStatut(Long id, StatutRendezVous statut) {
        Rendez_vous rv = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable"));

        rv.setStatut(statut);
        return rendezVousMapper.toDTO(rendezVousRepository.save(rv));
    }

}
