package assessment.Zorvyn_Project.repository;


import assessment.Zorvyn_Project.entity.FinancialRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinancialRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByDeletedFalse();
    List<FinancialRecord> findByDeletedFalseAndTypeIgnoreCase(String type);

    List<FinancialRecord> findByDeletedFalseAndCategoryIgnoreCase(String category);

    List<FinancialRecord> findByDeletedFalseAndDate(String date);
    Page<FinancialRecord> findByDeletedFalse(Pageable pageable);
    List<FinancialRecord> findByCategoryContainingIgnoreCaseAndDeletedFalse(String category);
    @Query("SELECT f FROM FinancialRecord f WHERE " +
            "LOWER(f.note) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(f.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<FinancialRecord> search(@Param("keyword") String keyword);
}
