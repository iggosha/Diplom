package bsuedu.golovkov.fintracker.service;

import bsuedu.golovkov.fintracker.entity.BankAccount;
import org.apache.poi.ss.usermodel.Sheet;

public interface BankAccountService {

    BankAccount getFromDbOrSheet(Sheet sheet);
}
