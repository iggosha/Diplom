package bsuedu.golovkov.fintracker.mapper;

import bsuedu.golovkov.fintracker.dto.request.FinOperationRequestDto;
import bsuedu.golovkov.fintracker.dto.response.FinOperationResponseDto;
import bsuedu.golovkov.fintracker.entity.FinOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Mapper
public interface FinOperationMapper {

    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "recordDate", source = "date")
    FinOperation toEntity(FinOperationRequestDto dto);

    FinOperationResponseDto toDto(FinOperation entity);

    List<FinOperationResponseDto> toDtoList(List<FinOperation> entityList);

    default String generateId() {
        return UUID.randomUUID().toString();
    }
}
