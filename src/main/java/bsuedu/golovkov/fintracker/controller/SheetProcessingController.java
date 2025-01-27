package bsuedu.golovkov.fintracker.controller;

import bsuedu.golovkov.fintracker.dto.response.RowResponseDto;
import bsuedu.golovkov.fintracker.service.SheetProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sheets")
public class SheetProcessingController {

    private final SheetProcessingService sheetProcessingService;

    @GetMapping(value = "/rows", consumes = "multipart/form-data")
    public List<RowResponseDto> getRowsFromFile(@RequestBody MultipartFile multipartFile) {
        return sheetProcessingService.getRowsFromFile(multipartFile);
    }

    @PostMapping(value = "/{rowNum}/{cellNum}", consumes = "multipart/form-data")
    public String getCell(@RequestBody MultipartFile multipartFile,
                          @PathVariable Integer rowNum,
                          @PathVariable Integer cellNum) {
        return sheetProcessingService.getCell(multipartFile, rowNum, cellNum);
    }
}
