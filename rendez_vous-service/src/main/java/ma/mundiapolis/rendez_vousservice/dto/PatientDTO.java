package ma.mundiapolis.rendez_vousservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {

    private String nom;
    private Integer age;
    private String adresse;
    private String contact;
}
