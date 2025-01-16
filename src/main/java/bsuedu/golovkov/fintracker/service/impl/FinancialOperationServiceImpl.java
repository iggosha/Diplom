package bsuedu.golovkov.fintracker.service.impl;

import bsuedu.golovkov.fintracker.dto.request.FinOperationRequestDto;
import bsuedu.golovkov.fintracker.dto.response.FinOperationResponseDto;
import bsuedu.golovkov.fintracker.entity.FinOperation;
import bsuedu.golovkov.fintracker.exception.ResourceNotFoundException;
import bsuedu.golovkov.fintracker.mapper.FinOperationMapper;
import bsuedu.golovkov.fintracker.repository.FinOperationRepository;
import bsuedu.golovkov.fintracker.service.FinancialOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinancialOperationServiceImpl implements FinancialOperationService {

    private final FinOperationRepository finOperationRepository;
    private final FinOperationMapper finOperationMapper;

    @Override
    public List<FinOperationResponseDto> getAll() {
        List<FinOperation> finOperationList = finOperationRepository.findAll();
        return finOperationMapper.toDtoList(finOperationList);
    }

    @Override
    public FinOperationResponseDto getById(UUID id) {
        FinOperation finOperation = finOperationRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return finOperationMapper.toDto(finOperation);
    }

    @Override
    public FinOperationResponseDto create(FinOperationRequestDto finOperationRequestDto) {
        FinOperation finOperation = finOperationMapper.toEntity(finOperationRequestDto);
        return finOperationMapper.toDto(finOperationRepository.save(finOperation));
    }
}
