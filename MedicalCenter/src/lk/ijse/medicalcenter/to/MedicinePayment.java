package lk.ijse.medicalcenter.to;


import java.time.LocalDate;

public class MedicinePayment {
    private String medicinePaymentId;
    private LocalDate date;
    private String supplierId;

    public MedicinePayment() {
    }

    public MedicinePayment(String medicinePaymentId, LocalDate date, String supplierId) {
        this.medicinePaymentId = medicinePaymentId;
        this.date = date;
        this.supplierId = supplierId;
    }

    public String getMedicinePaymentId() {
        return medicinePaymentId;
    }

    public void setMedicinePaymentId(String medicinePaymentId) {
        this.medicinePaymentId = medicinePaymentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

}
