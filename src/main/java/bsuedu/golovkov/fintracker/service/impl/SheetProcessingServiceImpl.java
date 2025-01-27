package bsuedu.golovkov.fintracker.service.impl;

import bsuedu.golovkov.fintracker.dto.response.RowResponseDto;
import bsuedu.golovkov.fintracker.service.SheetProcessingService;
import bsuedu.golovkov.fintracker.util.ParsingUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetProcessingServiceImpl implements SheetProcessingService {

    private final ParsingUtils parsingUtils;

    @Override
    public String getCell(MultipartFile multipartFile, Integer rowNum, Integer cellNum) {
        Sheet sheet = parsingUtils.getSheetFromMultipartFile(multipartFile);
        Row row = sheet.getRow(rowNum);
        return parsingUtils.getCellFromRowByNum(row, cellNum);
    }

    @Override
    public List<RowResponseDto> getRowsFromFile(MultipartFile multipartFile) {
        List<RowResponseDto> rows = new ArrayList<>();
        Sheet sheet = parsingUtils.getSheetFromMultipartFile(multipartFile);
        for (Row row : sheet) {
            List<String> cells = new ArrayList<>();
            for (Cell cell : row) {
                cells.add(cell.toString());
            }
            rows.add(new RowResponseDto(cells));
        }
        return rows;
    }
}
