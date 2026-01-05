package ma.mundiapolis.medecinservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedecinResDTO {
    private Long id;
    private String nom;
    private String specialite;
    private String contact;
}
