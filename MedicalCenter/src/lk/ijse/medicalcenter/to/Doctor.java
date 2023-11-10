package lk.ijse.medicalcenter.to;

public class Doctor {
    private String doctorId;
    private String name;
    private String specialty;
    private String address;
    private String gender;
    private int contact;
    private String dob;

    public Doctor() {
    }

    public Doctor(String doctorId, String name, String specialty, String address, String gender, int contact, String dob) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialty = specialty;
        this.address = address;
        this.gender = gender;
        this.contact = contact;
        this.dob = dob;
    }

    public Doctor(String doctorId, String name) {
        this.setDoctorId(doctorId);
        this.setName(name);
    }


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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
