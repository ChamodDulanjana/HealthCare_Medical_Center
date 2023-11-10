package lk.ijse.medicalcenter.to;

public class Receptionist {
    private String receptionistId;
    private String name;
    private String address;
    private String gender;
    private int contact;
    private String dob;

    public Receptionist(String receptionistId, String name) {
        this.receptionistId = receptionistId;
        this.name = name;
    }

    public Receptionist() {
    }

    public Receptionist(String receptionistId, String name, String address, String gender, int contact, String dob) {
        this.receptionistId = receptionistId;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.contact = contact;
        this.dob = dob;
    }

    public String getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(String receptionistId) {
        this.receptionistId = receptionistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
