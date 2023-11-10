package lk.ijse.medicalcenter.to;

public class Supplier {
    private String supId;
    private String name;
    private String company;
    private String address;
    private String gender;
    private int contact;

    public Supplier() {
    }

    public Supplier(String supId, String name, String company, String address, String gender, int contact) {
        this.supId = supId;
        this.name = name;
        this.company = company;
        this.address = address;
        this.gender = gender;
        this.contact = contact;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }
}
