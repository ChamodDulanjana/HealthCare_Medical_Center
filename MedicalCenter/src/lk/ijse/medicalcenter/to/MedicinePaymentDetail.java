package lk.ijse.medicalcenter.to;

public class MedicinePaymentDetail {
    private String medicinePaymentId;
    private String code;
    private int qty;
    private double unitPrice;

    public MedicinePaymentDetail() {
    }

    public MedicinePaymentDetail(String medicinePaymentId, String code, int qty, double unitPrice) {
        this.medicinePaymentId = medicinePaymentId;
        this.code = code;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getMedicinePaymentId() {
        return medicinePaymentId;
    }

    public void setMedicinePaymentId(String medicinePaymentId) {
        this.medicinePaymentId = medicinePaymentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
