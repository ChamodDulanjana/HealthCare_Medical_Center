package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.AppointmentModel;
import lk.ijse.medicalcenter.model.DoctorModel;
import lk.ijse.medicalcenter.model.PatientModel;
import lk.ijse.medicalcenter.to.Appointment;
import lk.ijse.medicalcenter.to.Doctor;
import lk.ijse.medicalcenter.to.Patient;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AppoForChannelingController {
    public Text txtAppId;
    public Text txtDate;
    public Text txtRepId;
    public JFXComboBox cmbDoctor;
    public JFXTextField txtPhoneNo;
    public Text txtPatientName;
    public Text txtPatientAddress;
    public Text txtPatientDob;
    public JFXComboBox cmbAppType;
    public JFXTextField txtSpecialty;
    public JFXButton btnEnter;
    private static String patientId;
    public AnchorPane pane;
    public JFXButton btnAppointment;
    public JFXComboBox cmbTest;
    public JFXTextField txtChannDate;
    public JFXTextField txtChannTime;
    public Text txtCurrentChannDate;
    public Text txtCurrentChannTime;
    private Pattern mobilePattern;
    private Pattern datePattern;
    private Pattern timePattern;

    public void initialize(){
        loadAppointmentIds();
        txtDate.setText(ClassGetData.loadDate());
        txtRepId.setText(ClassGetData.empId);
        loadCmbAppType();
        loadDoctors();
        loadTest();
        btnAppointment.setDisable(true);
        mobilePattern = Pattern.compile("^(075|077|072|076|078|071|011|070)([0-9]{7})$");
        datePattern = Pattern.compile("^[0-9/]{8,10}$");
        timePattern = Pattern.compile("^[0-9:]{4,5}$");
        loadCurrentAppointmentDateAndTime();
    }

    private void loadCurrentAppointmentDateAndTime() {
        String[] split = txtAppId.getText().split("A");
        int id = Integer.parseInt(split[1]);
        id --;
        String pastAppId = String.format("A%03d", id);
        try {
            Appointment appointment = AppointmentModel.getLastAppoDateAndTime(pastAppId);
            txtCurrentChannTime.setText(appointment.getChannelingTime());
            txtCurrentChannDate.setText(appointment.getChannelingDate());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadTest() {
        cmbTest.getItems().addAll("Blood","Urine");
    }


    private void loadDoctors() {
        try {
            ArrayList<Doctor> doctorsIdAndName = DoctorModel.getDoctorsIdAndName();
            for ( Doctor doctor : doctorsIdAndName) {
                cmbDoctor.getItems().addAll(doctor.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCmbAppType() {
        cmbAppType.getItems().addAll("Channeling","Testing");
    }

    private void loadAppointmentIds() {
        try {
            String lastAppId = AppointmentModel.generateNextAppId();
            txtAppId.setText(lastAppId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddAppointmentOnAction(ActionEvent actionEvent) {
        boolean isTimeMatched = timePattern.matcher(txtChannTime.getText()).matches();
        boolean isDateMatched = datePattern.matcher(txtChannDate.getText()).matches();
        if (isDateMatched){
            if (isTimeMatched){
                btnAddAppointmentOperation();
            }else {
                txtChannTime.setFocusColor(Paint.valueOf("Red"));
                txtChannTime.requestFocus();
            }
        }else {
            txtChannDate.setFocusColor(Paint.valueOf("Red"));
            txtChannDate.requestFocus();
        }
    }

    private void btnAddAppointmentOperation() {
        String test;
        if (cmbTest.getValue() == null){
            test = "-";
        }else {
            test = String.valueOf(cmbTest.getValue());
        }
        String doctor;
        if (cmbDoctor.getValue() == null){
            doctor = "-";
        }else {
            doctor = String.valueOf(cmbDoctor.getValue());
        }
        Appointment appointment = new Appointment(txtAppId.getText(),txtDate.getText(),txtRepId.getText(),String.valueOf(cmbAppType.getValue()),test,doctor,patientId,txtChannTime.getText(),txtChannDate.getText());
        try {
            boolean isAdded = AppointmentModel.addNewAppointment(appointment);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Appointment Added.",ButtonType.OK).show();
                clearFields();
                btnAppointment.setDisable(true);
                loadAppointmentIds();
                loadCurrentAppointmentDateAndTime();
            }else {
                new Alert(Alert.AlertType.WARNING,"Appointment Failed.",ButtonType.OK).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        cmbAppType.getSelectionModel().clearSelection();
        cmbDoctor.getSelectionModel().clearSelection();
        cmbTest.getSelectionModel().clearSelection();
        txtSpecialty.clear();
        txtPhoneNo.clear();
        txtPatientName.setText("");
        txtPatientAddress.setText("");
        txtPatientDob.setText("");
        txtChannTime.clear();
        txtChannDate.clear();
    }

    public void cmbDoctorOnAction(ActionEvent actionEvent) {
        try {
            String specialty = DoctorModel.getDoctorSpecialty(String.valueOf(cmbDoctor.getValue()));
            txtSpecialty.setText(specialty);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cmbAppTypeOnAction(ActionEvent actionEvent) {
        String selectedItem = String.valueOf(cmbAppType.getValue());
        if (selectedItem.equalsIgnoreCase("Testing")){
            cmbDoctor.getSelectionModel().clearSelection();
            txtSpecialty.clear();
            cmbDoctor.setDisable(true);
            txtSpecialty.setDisable(true);
            cmbTest.setDisable(false);
        }else {
            cmbDoctor.getSelectionModel().clearSelection();
            txtSpecialty.clear();
            cmbTest.getSelectionModel().clearSelection();
            cmbDoctor.setDisable(false);
            txtSpecialty.setDisable(false);
            cmbTest.setDisable(true);
        }
    }

    public void btnEnterOnAction(ActionEvent actionEvent) {
        boolean isMobileMatched = mobilePattern.matcher(txtPhoneNo.getText()).matches();
        if (cmbAppType.getValue() != null){
           switch (String.valueOf(cmbAppType.getValue())){
               case "Channeling" :
                   if (cmbDoctor.getValue() != null){
                       if (isMobileMatched){
                           btnEnterOperation();
                       }else {
                           txtPhoneNo.setFocusColor(Paint.valueOf("Red"));
                           txtPhoneNo.requestFocus();
                       }
                   }else {
                       cmbDoctor.setFocusColor(Paint.valueOf("Red"));
                       cmbDoctor.requestFocus();
                   }
                   break;
               case "Testing" :
                   if (cmbTest.getValue() != null){
                       if (isMobileMatched){
                           btnEnterOperation();
                       }else {
                           txtPhoneNo.setFocusColor(Paint.valueOf("Red"));
                           txtPhoneNo.requestFocus();
                       }
                   }else {
                       cmbTest.setFocusColor(Paint.valueOf("Red"));
                       cmbTest.requestFocus();
                   }
                   break;
           }
        } else {
            cmbAppType.setFocusColor(Paint.valueOf("Red"));
            cmbAppType.requestFocus();
        }
    }

    private void btnEnterOperation(){
        try {
            String name  = PatientModel.searchPatientUsingContact(txtPhoneNo.getText());
            patientId = PatientModel.getPatientId(txtPhoneNo.getText());
            if (name==null){
                btnEnter.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("Add Patient", ButtonBar.ButtonData.OK_DONE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "It's new number", ok);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(ok) == ok) {
                        try {
                            Navigation.navigate(Routes.Add_New_Patient,  pane);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }else {
                Patient patient = PatientModel.searchPatient(patientId);
                txtPatientName.setText(patient.getName());
                txtPatientAddress.setText(patient.getAddress());
                txtPatientDob.setText(patient.getDob());
                patientId = patient.getPatientId();
                btnAppointment.setDisable(false);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
