package bsuedu.golovkov.fintracker.service;

import bsuedu.golovkov.fintracker.dto.request.FinOperationRequestDto;
import bsuedu.golovkov.fintracker.dto.response.FinOperationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FinOperationService {

    Page<FinOperationResponseDto> getAllByPage(Pageable pageable);

    FinOperationResponseDto getById(String id);

    FinOperationResponseDto create(FinOperationRequestDto finOperationRequestDto);

    List<FinOperationResponseDto> createFromFile(MultipartFile multipartFile);

    void deleteAll();

    void deleteById(String id);

    List<FinOperationResponseDto> getAll();
}
