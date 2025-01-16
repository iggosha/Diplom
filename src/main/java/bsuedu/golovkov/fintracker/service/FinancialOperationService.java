package bsuedu.golovkov.fintracker.service;

import bsuedu.golovkov.fintracker.dto.request.FinOperationRequestDto;
import bsuedu.golovkov.fintracker.dto.response.FinOperationResponseDto;

import java.util.List;
import java.util.UUID;

public interface FinancialOperationService {

    List<FinOperationResponseDto> getAll();

    FinOperationResponseDto getById(UUID id);

    FinOperationResponseDto create(FinOperationRequestDto finOperationRequestDto);
}
