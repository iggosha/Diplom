package bsuedu.golovkov.fintracker.util;

import bsuedu.golovkov.fintracker.exception.ResourceIOException;
import bsuedu.golovkov.fintracker.exception.ResourceNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class ParsingUtils {

    public Sheet getSheetFromMultipartFile(MultipartFile multipartFile) {
        Workbook workbook;
        try (InputStream inputStream = multipartFile.getInputStream()) {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new ResourceIOException("File " + multipartFile.getOriginalFilename() + " processing exception: ", e);
        }
        return workbook.getSheetAt(0);
    }

    public String getCellFromRowNumAndSheetByNum(Sheet sheet, Integer rowNum, Integer cellNum) {
        Row row = Optional
                .of(sheet.getRow(rowNum))
                .orElseThrow(() -> new ResourceNotFoundException("No row was found with number " + rowNum));
        Cell cell = Optional
                .of(row.getCell(cellNum))
                .orElseThrow(() -> new ResourceNotFoundException("No cell was found with number " + cellNum));
        return cell.toString();
    }

    public String getCellFromRowByNum(Row row, Integer cellNum) {
        return row.getCell(cellNum).toString();
    }

    public LocalDate parseRuFormatDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public BigDecimal parseAmount(String amountString) {
        try {
            String cellNumber = amountString
                    .replaceAll("[^\\d,.-]", "")
                    .replace(",", ".");
            return new BigDecimal(cellNumber);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return null;
        }
    }
}
