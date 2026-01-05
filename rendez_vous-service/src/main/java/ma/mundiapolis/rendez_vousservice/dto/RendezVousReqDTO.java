package ma.mundiapolis.rendez_vousservice.dto;

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
public class RendezVousReqDTO {


    private Long patientId;
    private Long medecinId;
    private LocalDate date;
    private LocalTime heure;
    private StatutRendezVous statut;
    private String notes;
}

