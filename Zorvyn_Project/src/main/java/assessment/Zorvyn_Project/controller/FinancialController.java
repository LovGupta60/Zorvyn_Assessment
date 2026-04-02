package assessment.Zorvyn_Project.controller;


import assessment.Zorvyn_Project.dto.FinancialRecordDTO;
import assessment.Zorvyn_Project.entity.FinancialRecord;
import assessment.Zorvyn_Project.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public FinancialRecord create(@RequestBody FinancialRecordDTO dto){

        FinancialRecord r = new FinancialRecord();
        r.setAmount(dto.getAmount());
        r.setType(dto.getType());
        r.setCategory(dto.getCategory());
        r.setDate(dto.getDate());
        r.setNote(dto.getNote());

        return service.create(r);
    }
    @GetMapping("/filter")
    public List<FinancialRecord> filter(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String date
    ) {
        return service.filter(type, category, date);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ANALYST','ADMIN')")
    public List<FinancialRecord> getAll(){
        return service.getAll();
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FinancialRecord update(@PathVariable Long id,
                                  @RequestBody FinancialRecord r){
        return service.update(id,r);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
