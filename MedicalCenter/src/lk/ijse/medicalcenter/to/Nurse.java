package lk.ijse.medicalcenter.to;

public class Nurse {
    private String nurseId;
    private String name;
    private String address;
    private String gender;
    private int contact;
    private String dob;

    public Nurse() {
    }

    public Nurse(String nurseId, String name) {
        this.nurseId = nurseId;
        this.name = name;
    }

    public Nurse(String nurseId, String name, String address, String gender, int contact, String dob) {
        this.nurseId = nurseId;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.contact = contact;
        this.dob = dob;
    }


    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
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
