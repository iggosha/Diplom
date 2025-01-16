package bsuedu.golovkov.fintracker.controller;

import bsuedu.golovkov.fintracker.dto.request.FinOperationRequestDto;
import bsuedu.golovkov.fintracker.dto.response.FinOperationResponseDto;
import bsuedu.golovkov.fintracker.service.FinancialOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/fin-ops")
public class FinOperationController {

    private final FinancialOperationService financialOperationService;

    @GetMapping
    public List<FinOperationResponseDto> getAll() {
        return financialOperationService.getAll();
    }

    @GetMapping("/id")
    public FinOperationResponseDto getById(UUID id) {
        return financialOperationService.getById(id);
    }

    @PostMapping
    public FinOperationResponseDto create(FinOperationRequestDto finOperationRequestDto) {
        return financialOperationService.create(finOperationRequestDto);
    }
}
