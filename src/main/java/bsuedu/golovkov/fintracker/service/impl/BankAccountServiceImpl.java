package bsuedu.golovkov.fintracker.service.impl;

import bsuedu.golovkov.fintracker.entity.BankAccount;
import bsuedu.golovkov.fintracker.repository.BankAccountRepository;
import bsuedu.golovkov.fintracker.service.BankAccountService;
import bsuedu.golovkov.fintracker.util.BankAccountParser;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountParser bankAccountParser;
    private final BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount getBankAccount(Sheet sheet) {
        String bankAccountId = bankAccountParser.getAccountId(sheet);
        return bankAccountRepository
                .findById(bankAccountId)
                .orElseGet(() -> {
                    BankAccount parsedAccount = bankAccountParser.parse(sheet, bankAccountId);
                    return bankAccountRepository.save(parsedAccount);
                });
    }
}
