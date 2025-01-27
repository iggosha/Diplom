package bsuedu.golovkov.fintracker.util;

import bsuedu.golovkov.fintracker.entity.FinOperation;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class FinOperationParser {

    private final ParsingUtils parsingUtils;

    public FinOperation parse(String id, Row row) {
        return FinOperation.builder()
                .date(parseDate(row))
                .recordDate(parseRecordDate(row))
                .id(id)
                .category(parseCategory(row))
                .description(parseDescription(row))
                .amount(parseAmount(row))
                .status(parseStatus(row))
                .build();
    }

    private LocalDate parseDate(Row row) {
        return parsingUtils.parseRuFormatDate(parsingUtils.getCellFromRowByNum(row, 0));
    }

    private LocalDate parseRecordDate(Row row) {
        return parsingUtils.parseRuFormatDate(parsingUtils.getCellFromRowByNum(row, 1));
    }

    public String parseId(Row row) {
        return parsingUtils.getCellFromRowByNum(row, 3);
    }

    private String parseCategory(Row row) {
        return parsingUtils.getCellFromRowByNum(row, 4);
    }

    private String parseDescription(Row row) {
        return parsingUtils.getCellFromRowByNum(row, 11);
    }

    private BigDecimal parseAmount(Row row) {
        return parsingUtils.parseAmount(parsingUtils.getCellFromRowByNum(row, 12));
    }

    private String parseStatus(Row row) {
        return parsingUtils.getCellFromRowByNum(row, 14);
    }
}