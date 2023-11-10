package lk.ijse.medicalcenter.to;

public class MedicineStore {
    private String code;
    private String description;
    private String company;
    private String weight;
    private double unitPrice;
    private int qtyOnHand;

    public MedicineStore() {
    }

    public MedicineStore(String code, String description, String company, String weight, double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.company = company;
        this.weight = weight;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public MedicineStore(String code, String description, String weight, double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.weight = weight;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
