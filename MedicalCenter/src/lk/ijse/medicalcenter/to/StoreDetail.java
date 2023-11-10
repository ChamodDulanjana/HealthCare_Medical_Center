package lk.ijse.medicalcenter.to;

public class StoreDetail {
    private String medicinePaymentId;
    private String code;
    private String description;
    private String company;
    private String weight;
    private double unitPrice;
    private int qty;

    public StoreDetail() {
    }

    public StoreDetail(String medicinePaymentId, String code, String description, String company, String weight, double unitPrice, int qtyOnHand) {
        this.medicinePaymentId = medicinePaymentId;
        this.code = code;
        this.description = description;
        this.company = company;
        this.weight = weight;
        this.unitPrice = unitPrice;
        this.qty = qtyOnHand;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public void setQty(int qtyOnHand) {
        this.qty = qtyOnHand;
    }
}
