package bsuedu.golovkov.fintracker.repository;

import bsuedu.golovkov.fintracker.entity.FinOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinOperationRepository extends JpaRepository<FinOperation, String> {

}
