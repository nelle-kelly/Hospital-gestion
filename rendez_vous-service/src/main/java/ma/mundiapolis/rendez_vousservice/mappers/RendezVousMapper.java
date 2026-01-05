package ma.mundiapolis.rendez_vousservice.mappers;


import ma.mundiapolis.rendez_vousservice.dto.RendezVousReqDTO;
import ma.mundiapolis.rendez_vousservice.dto.RendezVousResDTO;
import ma.mundiapolis.rendez_vousservice.entities.Rendez_vous;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RendezVousMapper {

    RendezVousResDTO toDTO(Rendez_vous rendezVous);

    Rendez_vous toEntity(RendezVousReqDTO rendezVousReqDTO);

    List<RendezVousResDTO> toDTOList(List<Rendez_vous> rendezVousList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(RendezVousReqDTO rendezVousReqDTO, @MappingTarget Rendez_vous rendezVous);
}
