package com.app.manager.mappers;

import com.app.manager.dto.AdmDto;
import com.app.manager.entity.AdmEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdmMapper {
    AdmEntity toModel(AdmDto admDto);
    AdmDto toDto(AdmEntity admEntity);
}
