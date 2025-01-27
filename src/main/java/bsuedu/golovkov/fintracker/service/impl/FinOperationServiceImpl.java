package bsuedu.golovkov.fintracker.service.impl;

import bsuedu.golovkov.fintracker.dto.request.FinOperationRequestDto;
import bsuedu.golovkov.fintracker.dto.response.FinOperationResponseDto;
import bsuedu.golovkov.fintracker.entity.BankAccount;
import bsuedu.golovkov.fintracker.entity.FinOperation;
import bsuedu.golovkov.fintracker.exception.ResourceAlreadyExistsException;
import bsuedu.golovkov.fintracker.exception.ResourceNotFoundException;
import bsuedu.golovkov.fintracker.mapper.FinOperationMapper;
import bsuedu.golovkov.fintracker.repository.FinOperationRepository;
import bsuedu.golovkov.fintracker.service.FinOperationService;
import bsuedu.golovkov.fintracker.util.FinOperationParser;
import bsuedu.golovkov.fintracker.util.ParsingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FinOperationServiceImpl implements FinOperationService {

    private final FinOperationRepository finOperationRepository;
    private final FinOperationMapper finOperationMapper;
    private final FinOperationParser finOperationParser;
    private final ParsingUtils parsingUtils;
    private final BankAccountServiceImpl bankAccountServiceImpl;

    @Override
    public Page<FinOperationResponseDto> getAllByPage(Pageable pageable) {
        Page<FinOperation> finOperationPage = finOperationRepository.findAll(pageable);
        List<FinOperationResponseDto> dtoList = finOperationMapper.toDtoList(finOperationPage.getContent());
        return new PageImpl<>(dtoList, pageable, finOperationPage.getTotalPages());
    }

    @Override
    public List<FinOperationResponseDto> getAll() {
        List<FinOperation> finOperationList = finOperationRepository.findAll();
        return finOperationMapper.toDtoList(finOperationList);
    }

    @Override
    public void deleteAll() {
        finOperationRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        finOperationRepository.deleteById(id);
    }

    @Override
    public FinOperationResponseDto getById(String id) {
        FinOperation finOperation = finOperationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No financial operation was found with ID " + id));
        return finOperationMapper.toDto(finOperation);
    }

    @Override
    public FinOperationResponseDto create(FinOperationRequestDto finOperationRequestDto) {
        FinOperation finOperation = finOperationMapper.toEntity(finOperationRequestDto);
        if (finOperationRepository.existsById(finOperation.getId())) {
            throw new ResourceAlreadyExistsException("Financial operation with ID " + finOperation.getId()
                    + " already exists. Please, try again.");
        }
        return finOperationMapper.toDto(finOperationRepository.save(finOperation));
    }

    @Override
    public List<FinOperationResponseDto> createFromFile(MultipartFile file) {
        List<FinOperation> finOperations = new ArrayList<>();
        Sheet sheet = parsingUtils.getSheetFromMultipartFile(file);
        BankAccount bankAccount = bankAccountServiceImpl.getBankAccount(sheet);
        for (Row row : sheet) {
            if (row.getRowNum() < 20) continue;
            FinOperation finOperation = getFinOperation(row, bankAccount);
            finOperations.add(finOperationRepository.save(finOperation));
        }
        return finOperationMapper.toDtoList(finOperations);
    }

    private FinOperation getFinOperation(Row row, BankAccount bankAccount) {
        String finOperationId = finOperationParser.parseId(row);
        return finOperationRepository.findById(finOperationId).orElseGet(() -> {
            FinOperation parsedOperation = finOperationParser.parse(finOperationId, row);
            parsedOperation.setBankAccount(bankAccount);
            return parsedOperation;
        });
    }
}
