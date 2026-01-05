package ma.mundiapolis.patientservice.mappers;


import ma.mundiapolis.patientservice.dto.PatientReqDTO;
import ma.mundiapolis.patientservice.dto.PatientResDTO;
import ma.mundiapolis.patientservice.entities.Patient;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient toEntity(PatientReqDTO patientReqDTO);

    PatientResDTO toDTO(Patient patient);

    List<PatientResDTO> toDTOList(List<Patient> patients);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(PatientReqDTO patientReqDTO, @MappingTarget Patient patient);
}
