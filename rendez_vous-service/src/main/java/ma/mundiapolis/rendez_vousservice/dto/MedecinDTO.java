package ma.mundiapolis.rendez_vousservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedecinDTO {

    private String nom;
    private String specialite;
    private String contact;
}
