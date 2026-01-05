package ma.mundiapolis.patientservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientReqDTO {
    private String nom;
    private Integer age;
    private String adresse;
    private String contact;

}
