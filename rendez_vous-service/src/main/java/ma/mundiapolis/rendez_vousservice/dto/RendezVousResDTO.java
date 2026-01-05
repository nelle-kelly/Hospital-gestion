package ma.mundiapolis.rendez_vousservice.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVousResDTO {

    private Long id;
    private PatientDTO patient;
    private MedecinDTO medecin;
    private LocalDate date;
    private LocalTime heure;
    private String statut;
    private String notes;
}

