package assessment.Zorvyn_Project.dto;


import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class FinancialRecordDTO {

    @NotNull
    private Double amount;

    @NotNull
    private String type;

    private String category;
    private LocalDate date;
    private String note;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
