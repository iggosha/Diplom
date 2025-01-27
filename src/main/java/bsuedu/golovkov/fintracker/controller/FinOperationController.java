package bsuedu.golovkov.fintracker.controller;

import bsuedu.golovkov.fintracker.dto.request.FinOperationRequestDto;
import bsuedu.golovkov.fintracker.dto.response.FinOperationResponseDto;
import bsuedu.golovkov.fintracker.service.FinOperationService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fin-ops")
public class FinOperationController {

    private final FinOperationService finOperationService;

    @GetMapping
    public List<FinOperationResponseDto> getAll() {
        return finOperationService.getAll();
    }

    @GetMapping("/page")
    public Page<FinOperationResponseDto> getAllByPage(
            @ParameterObject @PageableDefault(sort = "date", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return finOperationService.getAllByPage(pageable);
    }


    @GetMapping("/{id}")
    public FinOperationResponseDto getById(@PathVariable String id) {
        return finOperationService.getById(id);
    }

    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public List<FinOperationResponseDto> createFromFile(@RequestBody MultipartFile multipartFile) {
        return finOperationService.createFromFile(multipartFile);
    }

    @PostMapping("/single")
    public FinOperationResponseDto create(FinOperationRequestDto finOperationRequestDto) {
        return finOperationService.create(finOperationRequestDto);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping
    public void deleteAll() {
        finOperationService.deleteAll();
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        finOperationService.deleteById(id);
    }
}
