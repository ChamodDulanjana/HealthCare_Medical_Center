package lk.ijse.medicalcenter.to;

import rex.utils.S;

public class MedicineExpenses {
    private String medicinePaymentId;
    private String date;
    private double amount;

    public MedicineExpenses() {
    }

    public MedicineExpenses(String medicinePaymentId, String date, double amount) {
        this.medicinePaymentId = medicinePaymentId;
        this.date = date;
        this.amount = amount;
    }

    public String getMedicinePaymentId() {
        return medicinePaymentId;
    }

    public void setMedicinePaymentId(String medicinePaymentId) {
        this.medicinePaymentId = medicinePaymentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
