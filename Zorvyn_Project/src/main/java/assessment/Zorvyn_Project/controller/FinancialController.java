package assessment.Zorvyn_Project.controller;

import assessment.Zorvyn_Project.dto.FinancialRecordDTO;
import assessment.Zorvyn_Project.entity.FinancialRecord;
import assessment.Zorvyn_Project.service.FinancialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analyst/records")
public class FinancialController {

    @Autowired
    private FinancialService service;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FinancialRecord> create(@Valid @RequestBody FinancialRecordDTO dto){
        FinancialRecord r = new FinancialRecord();
        r.setAmount(dto.getAmount());
        r.setType(dto.getType());
        r.setCategory(dto.getCategory());
        r.setDate(dto.getDate());
        r.setNote(dto.getNote());
        return ResponseEntity.ok(service.create(r));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<FinancialRecord>> filter(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String date
    ) {
        return ResponseEntity.ok(service.filter(type, category, date));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ANALYST','ADMIN')")
    public ResponseEntity<Page<FinancialRecord>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FinancialRecord> update(@PathVariable Long id, @RequestBody FinancialRecord r){
        return ResponseEntity.ok(service.update(id, r));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Record soft deleted");
    }

    @DeleteMapping("/permanent/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> del(@PathVariable Long id) {
        service.del(id);
        return ResponseEntity.ok("Record permanently deleted");
    }
    @GetMapping("/search")
    public ResponseEntity<List<FinancialRecord>> search(@RequestParam String keyword){
        return ResponseEntity.ok(service.search(keyword));
    }
}