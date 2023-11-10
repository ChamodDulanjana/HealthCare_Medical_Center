package lk.ijse.medicalcenter.to;

public class PaymentDetail {
    private String paymentId;
    private String code;
    private String description;
    private String weight;
    private double unitPrice;
    private int qty;

    public PaymentDetail() {
    }

    public PaymentDetail(double unitPrice, int qty) {
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public PaymentDetail(String paymentId, String code, String description, String weight, double unitPrice, int qty) {
        this.paymentId = paymentId;
        this.code = code;
        this.description = description;
        this.weight = weight;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
