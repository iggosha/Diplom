package bsuedu.golovkov.fintracker.mapper;

import bsuedu.golovkov.fintracker.dto.request.FinOperationRequestDto;
import bsuedu.golovkov.fintracker.dto.response.FinOperationResponseDto;
import bsuedu.golovkov.fintracker.entity.FinOperation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FinOperationMapper {

    FinOperation toEntity(FinOperationRequestDto dto);

    FinOperationResponseDto toDto(FinOperation entity);

    List<FinOperationResponseDto> toDtoList(List<FinOperation> entityList);
}
