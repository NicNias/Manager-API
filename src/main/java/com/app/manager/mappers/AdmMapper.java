package com.app.manager.mappers;

import com.app.manager.dto.AdmDto;
import com.app.manager.dto.AdmInfoDto;
import com.app.manager.entity.AdmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdmMapper {
    AdmEntity toModel(AdmDto admDto);
    AdmDto toDto(AdmEntity admEntity);
    AdmInfoDto toInfoDto(AdmEntity admEntity);

    List<AdmInfoDto> ListAdmDto(List<AdmEntity> adms);

    void updateEntityFromDto(AdmDto admDto, @MappingTarget AdmEntity admEntity);
}
