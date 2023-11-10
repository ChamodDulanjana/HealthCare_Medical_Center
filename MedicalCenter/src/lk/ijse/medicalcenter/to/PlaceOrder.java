package lk.ijse.medicalcenter.to;

import java.util.ArrayList;

public class PlaceOrder {
    private String paymentId;
    private String patientId;
    private String nurseId;
    private ArrayList<PaymentDetail> paymentDetailsList = new ArrayList<>();

    public PlaceOrder() {
    }

    public PlaceOrder(String paymentId, String patientId, String nurseId, ArrayList<PaymentDetail> paymentDetailsList) {
        this.paymentId = paymentId;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.paymentDetailsList = paymentDetailsList;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public ArrayList<PaymentDetail> getPaymentDetailsList() {
        return paymentDetailsList;
    }

    public void setPaymentDetailsList(ArrayList<PaymentDetail> paymentDetailsList) {
        this.paymentDetailsList = paymentDetailsList;
    }
}
