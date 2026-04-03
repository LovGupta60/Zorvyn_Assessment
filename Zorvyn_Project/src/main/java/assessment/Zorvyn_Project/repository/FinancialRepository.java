package assessment.Zorvyn_Project.repository;


import assessment.Zorvyn_Project.entity.FinancialRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByDeletedFalse();
    List<FinancialRecord> findByCategoryAndDeletedFalse(String category);
    List<FinancialRecord> findByDeletedFalseAndTypeIgnoreCase(String type);

    List<FinancialRecord> findByDeletedFalseAndCategoryIgnoreCase(String category);

    List<FinancialRecord> findByDeletedFalseAndDate(String date);
    Page<FinancialRecord> findByDeletedFalse(Pageable pageable);
    List<FinancialRecord> findByCategoryContainingIgnoreCaseAndDeletedFalse(String category);
}
