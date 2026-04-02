package assessment.Zorvyn_Project.service;


import assessment.Zorvyn_Project.entity.FinancialRecord;
import assessment.Zorvyn_Project.repository.FinancialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private FinancialRepository repo;

    public Map<String, Double> summary() {

        List<FinancialRecord> list = repo.findByDeletedFalse();

        if (list == null || list.isEmpty()) {
            Map<String, Double> empty = new HashMap<>();
            empty.put("income", 0.0);
            empty.put("expense", 0.0);
            empty.put("balance", 0.0);
            return empty;
        }

        double income = list.stream()
                .filter(x -> "income".equalsIgnoreCase(x.getType()))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double expense = list.stream()
                .filter(x -> "expense".equalsIgnoreCase(x.getType()))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        Map<String, Double> map = new HashMap<>();
        map.put("income", income);
        map.put("expense", expense);
        map.put("balance", income - expense);

        return map;
    }
    public Map<String, Double> categoryWise() {

        List<FinancialRecord> list = repo.findByDeletedFalse();

        Map<String, Double> map = new HashMap<>();

        for (FinancialRecord r : list) {
            map.put(
                    r.getCategory(),
                    map.getOrDefault(r.getCategory(), 0.0) + r.getAmount()
            );
        }

        return map;
    }
    public Map<String, Double> monthly() {
        List<FinancialRecord> list = repo.findByDeletedFalse();
        Map<String, Double> map = new HashMap<>();

        for (FinancialRecord r : list) {
            String month = r.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            map.put(month, map.getOrDefault(month, 0.0) + r.getAmount());
        }

        return map;
    }
    public List<FinancialRecord> recent() {

        return repo.findByDeletedFalse()
                .stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(5)
                .toList();
    }
}
