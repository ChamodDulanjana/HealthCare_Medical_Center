package lk.ijse.medicalcenter.view.tm;

public class IssueBillTM {
    private String code;
    private String description;
    private String weight;
    private int qty;
    private double unitPrice;
    private double total;

    public IssueBillTM() {
    }

    public IssueBillTM(String code, String description, String weight, int qty, double unitPrice, double total) {
        this.code = code;
        this.description = description;
        this.weight = weight;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
