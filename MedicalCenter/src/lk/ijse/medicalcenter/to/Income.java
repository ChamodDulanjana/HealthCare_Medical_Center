package lk.ijse.medicalcenter.to;

public class Income {
    private String paymentId;
    private String date;
    private String month;
    private String year;
    private double amount;

    public Income() {
    }

    public Income(String paymentId, String date, String month, String year, double amount) {
        this.paymentId = paymentId;
        this.date = date;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
