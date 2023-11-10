package lk.ijse.medicalcenter.to;

public class Appointment {
    private String appointmentId;
    private String date;
    private String receptionistId;
    private String appointmentType;
    private String test;
    private String doctor;
    private String patientId;
    private String channelingTime;
    private String channelingDate;

    public Appointment(String appointmentId, String date, String receptionistId, String appointmentType, String test, String doctor, String patientId, String channelingTime, String channelingDate) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.receptionistId = receptionistId;
        this.appointmentType = appointmentType;
        this.test = test;
        this.doctor = doctor;
        this.patientId = patientId;
        this.channelingTime = channelingTime;
        this.channelingDate = channelingDate;
    }

    public Appointment(String channelingTime, String channelingDate) {
        this.channelingTime = channelingTime;
        this.channelingDate = channelingDate;
    }

    public Appointment() {
    }


    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(String receptionistId) {
        this.receptionistId = receptionistId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getChannelingTime() {
        return channelingTime;
    }

    public void setChannelingTime(String channelingTime) {
        this.channelingTime = channelingTime;
    }

    public String getChannelingDate() {
        return channelingDate;
    }

    public void setChannelingDate(String channelingDate) {
        this.channelingDate = channelingDate;
    }
}
