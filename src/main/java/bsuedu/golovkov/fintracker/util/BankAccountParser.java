package bsuedu.golovkov.fintracker.util;

import bsuedu.golovkov.fintracker.entity.BankAccount;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BankAccountParser {

    private final ParsingUtils parsingUtils;

    public BankAccount parse(Sheet sheet, String id) {
        return BankAccount.builder()
                .id(id)
                .creationDate(parseCreationDate(sheet))
                .currency(parseCurrency(sheet))
                .balance(parseBalance(sheet))
                .build();
    }

    public String getAccountId(Sheet sheet) {
        return parsingUtils.getCellFromRowNumAndSheetByNum(sheet, 7, 2);
    }

    private LocalDate parseCreationDate(Sheet sheet) {
        String cellValue = parsingUtils.getCellFromRowNumAndSheetByNum(sheet, 8, 2);
        return parsingUtils.parseRuFormatDate(cellValue);
    }

    private String parseCurrency(Sheet sheet) {
        return parsingUtils.getCellFromRowNumAndSheetByNum(sheet, 9, 2);
    }

    private BigDecimal parseBalance(Sheet sheet) {
        return parsingUtils.parseAmount(
                parsingUtils.getCellFromRowNumAndSheetByNum(sheet, 13, 13)
        );
    }
}