package assessment.Zorvyn_Project.controller;

import assessment.Zorvyn_Project.entity.FinancialRecord;
import assessment.Zorvyn_Project.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Double>> getSummary(){
        return ResponseEntity.ok(service.summary());
    }

    @GetMapping("/category")
    public ResponseEntity<Map<String, Double>> categoryWise() {
        return ResponseEntity.ok(service.categoryWise());
    }

    @GetMapping("/recent")
    public ResponseEntity<List<FinancialRecord>> recent() {
        return ResponseEntity.ok(service.recent());
    }

    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Double>> monthly() {
        return ResponseEntity.ok(service.monthly());
    }
}