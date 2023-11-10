package lk.ijse.medicalcenter.to;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestPayment {
    private String paymentId;
    private LocalDate date;
    private String patientId;
    private String nurseId;
    private ArrayList<Test> testArrayList = new ArrayList<>();

    public TestPayment() {
    }

    public TestPayment(String paymentId, LocalDate date, String patientId, String nurseId, ArrayList<Test> testArrayList) {
        this.paymentId = paymentId;
        this.date = date;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.testArrayList = testArrayList;
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

    public ArrayList<Test> getTestArrayList() {
        return testArrayList;
    }

    public void setTestArrayList(ArrayList<Test> testArrayList) {
        this.testArrayList = testArrayList;
    }
}
