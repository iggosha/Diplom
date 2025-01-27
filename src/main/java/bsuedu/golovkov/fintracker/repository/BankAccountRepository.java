package bsuedu.golovkov.fintracker.repository;

import bsuedu.golovkov.fintracker.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}