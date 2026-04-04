package assessment.Zorvyn_Project.service;



import assessment.Zorvyn_Project.entity.FinancialRecord;
import assessment.Zorvyn_Project.repository.FinancialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialService {

    @Autowired
    private FinancialRepository repo;

    public FinancialRecord create(FinancialRecord r) {
        return repo.save(r);
    }
    public List<FinancialRecord> filter(String type, String category, String date) {

        if (type != null) {
            return repo.findByDeletedFalseAndTypeIgnoreCase(type);
        }

        if (category != null) {
            return repo.findByDeletedFalseAndCategoryIgnoreCase(category);
        }

        if (date != null) {
            return repo.findByDeletedFalseAndDate(date);
        }

        return repo.findByDeletedFalse();
    }
    public Page<FinancialRecord> getAll(int page, int size) {
        return repo.findByDeletedFalse(PageRequest.of(page, size));
    }

    public FinancialRecord update(Long id, FinancialRecord r) {
        FinancialRecord old = repo.findById(id).orElseThrow();
        old.setAmount(r.getAmount());
        old.setCategory(r.getCategory());
        old.setType(r.getType());
        return repo.save(old);
    }

    public void delete(Long id) {
        FinancialRecord r = repo.findById(id).orElseThrow();
        r.setDeleted(true);
        repo.save(r);
    }
    public void del(Long id) {
        repo.deleteById(id);
    }
    public List<FinancialRecord> search(String keyword) {

        return repo
                .search(keyword);
    }
}