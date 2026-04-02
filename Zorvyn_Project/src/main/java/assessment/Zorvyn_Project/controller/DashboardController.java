package assessment.Zorvyn_Project.controller;


import assessment.Zorvyn_Project.entity.FinancialRecord;
import assessment.Zorvyn_Project.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/summary")
    public Map<String, Double> getSummary(){
        return service.summary();
    }
    @GetMapping("/category")
    public Map<String, Double> categoryWise() {
        return service.categoryWise();
    }
    @GetMapping("/recent")
    public List<FinancialRecord> recent() {
        return service.recent();
    }
    @GetMapping("/monthly")
    public Map<String, Double> monthly() {
        return service.monthly();
    }
}
