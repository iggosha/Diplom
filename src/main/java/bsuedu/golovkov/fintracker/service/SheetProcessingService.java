package bsuedu.golovkov.fintracker.service;

import bsuedu.golovkov.fintracker.dto.response.RowResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SheetProcessingService {

    String getCell(MultipartFile multipartFile, Integer rowNum, Integer cellNum);

    List<RowResponseDto> getRowsFromFile(MultipartFile multipartFile);
}
