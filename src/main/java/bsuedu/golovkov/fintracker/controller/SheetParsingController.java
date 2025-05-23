package bsuedu.golovkov.fintracker.controller;

import bsuedu.golovkov.fintracker.dto.response.RowResponseDto;
import bsuedu.golovkov.fintracker.service.SheetParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//todo clear
@RestController
@RequiredArgsConstructor
@RequestMapping("/sheets")
public class SheetParsingController {

    private final SheetParsingService sheetParsingService;

    @GetMapping(value = "/rows", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<RowResponseDto> getRowsFromFile(@RequestBody MultipartFile multipartFile) {
        return sheetParsingService.getRowsFromFile(multipartFile);
    }

    @PostMapping(value = "/{rowNum}/{cellNum}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String getCell(@RequestBody MultipartFile multipartFile,
                          @PathVariable Integer rowNum,
                          @PathVariable Integer cellNum) {
        return sheetParsingService.getCell(multipartFile, rowNum, cellNum);
    }
}
