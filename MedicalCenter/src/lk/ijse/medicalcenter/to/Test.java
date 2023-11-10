package lk.ijse.medicalcenter.to;

public class Test {
    private String testId;
    private String description;
    private double totalFee;
    private String paymentId;

    public Test() {
    }

    public Test(String testId, String description, double totalFee, String paymentId) {
        this.testId = testId;
        this.description = description;
        this.totalFee = totalFee;
        this.paymentId = paymentId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
