package ma.mundiapolis.medecinservice.mappers;

import ma.mundiapolis.medecinservice.dto.MedecinReqDTO;
import ma.mundiapolis.medecinservice.dto.MedecinResDTO;
import ma.mundiapolis.medecinservice.entities.Medecin;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedecinMapper {

    MedecinResDTO toDTO(Medecin medecin);

    Medecin toEntity(MedecinReqDTO medecinReqDTO);

    List<MedecinResDTO> toDTOList(List<Medecin> medecins);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(MedecinReqDTO medecinReqDTO, @MappingTarget Medecin medecin);
}
