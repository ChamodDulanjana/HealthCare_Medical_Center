package lk.ijse.medicalcenter.to;

import java.time.LocalDate;

public class Payment {
    private String paymentId;
    private LocalDate date;
    private String patientId;
    private String nurseId;

    public Payment() {
    }

    public Payment(String paymentId, LocalDate date, String patientId, String nurseId) {
        this.paymentId = paymentId;
        this.date = date;
        this.patientId = patientId;
        this.nurseId = nurseId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }
}
