package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.*;
import lk.ijse.medicalcenter.to.*;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ManageFormSliderController {

    public AnchorPane pane;
    public AnchorPane paneTwo;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDob;
    public JFXTextField txtCompany;
    public JFXComboBox cmbRole;
    public JFXTextField txtSpecialty;
    public JFXComboBox cmbGender;
    public Pattern textPattern;
    private Pattern mobilePattern;
    private Pattern dobPattern;

    public void initialize(){
        ClassGetData.loadRole(cmbRole);
        cmbGender.getItems().addAll("Male","Female");
        textPattern = Pattern.compile("^[A-Za-z]{1,}$");
        mobilePattern = Pattern.compile("^(075|077|072|076|078|071|011|070)([0-9]{7})$");
        dobPattern = Pattern.compile("^[0-9/]{8,10}$");
    }

    public void btnAddFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Add_Form, paneTwo);
    }

    public void btnSearchFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Search_Form, paneTwo);
    }

    public void btnUpdateFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Update_Form, paneTwo);
    }

    public void btnDeleteFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Delete_Form, paneTwo);
    }

    public void btnViewDetailFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.View_Detail_Form, paneTwo);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isNameMatched = textPattern.matcher(txtName.getText()).matches();
        boolean isCompanyMatched = textPattern.matcher(txtCompany.getText()).matches();
        boolean isSpecialtyMatched = textPattern.matcher(txtSpecialty.getText()).matches();
        boolean isAddressMatched = textPattern.matcher(txtAddress.getText()).matches();
        boolean isMobileMatched = mobilePattern.matcher(txtContact.getText()).matches();
        boolean isDobMatched = dobPattern.matcher(txtDob.getText()).matches();

        String role = String.valueOf(cmbRole.getValue());
        if (cmbRole.getValue() != null){
            if (isNameMatched){
                if (isAddressMatched){
                    if (cmbGender.getValue() != null){
                        if (isMobileMatched){
                            switch (role){
                                case "Nurse" :
                                case "Receptionist" :
                                case "Patient" :
                                    if (isDobMatched){
                                        btnAddOperation(role);
                                    }else {
                                        txtDob.setFocusColor(Paint.valueOf("Red"));
                                        txtDob.requestFocus();
                                    }
                                    break;
                                case "Doctor" :
                                    if (isSpecialtyMatched){
                                        if (txtDob.getText().isEmpty() == false){
                                            btnAddOperation(role);
                                        }else {
                                            txtDob.setFocusColor(Paint.valueOf("Red"));
                                            txtDob.requestFocus();
                                        }
                                    }else {
                                        txtSpecialty.setFocusColor(Paint.valueOf("Red"));
                                        txtSpecialty.requestFocus();
                                    }
                                    break;
                                case "Supplier" :
                                    if (isCompanyMatched){
                                        btnAddOperation(role);
                                    }else {
                                        txtCompany.setFocusColor(Paint.valueOf("Red"));
                                        txtCompany.requestFocus();
                                    }
                                    break;
                            }
                        }else {
                            txtContact.setFocusColor(Paint.valueOf("Red"));
                            txtContact.requestFocus();
                        }
                    }else {
                        cmbGender.setFocusColor(Paint.valueOf("Red"));
                        cmbGender.requestFocus();
                    }
                }else {
                    txtAddress.setFocusColor(Paint.valueOf("Red"));
                    txtAddress.requestFocus();
                }
            }else {
                txtName.setFocusColor(Paint.valueOf("Red"));
                txtName.requestFocus();
            }
        }else {
            cmbRole.setFocusColor(Paint.valueOf("Red"));
            cmbRole.requestFocus();
        }
    }

    private void btnAddOperation(String role) {
        switch (role){
            case "Supplier" :
                Supplier supplier = new Supplier(txtId.getText(), txtName.getText(), txtCompany.getText(), txtAddress.getText(), String.valueOf(cmbGender.getValue()), Integer.valueOf(txtContact.getText()));
                try {
                    boolean isAdded = SupplierModel.addNewSupplier(supplier);
                    isAddSuccess(isAdded);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "Nurse" :
                Nurse nurse = new Nurse(txtId.getText(), txtName.getText(), txtAddress.getText(), String.valueOf(cmbGender.getValue()), Integer.valueOf(txtContact.getText()), txtDob.getText());
                try {
                    boolean isAdded = NurseModel.addNewNurse(nurse);
                    isAddSuccess(isAdded);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "Doctor" :
                Doctor doctor = new Doctor(txtId.getText(), txtName.getText(),txtSpecialty.getText(), txtAddress.getText(), String.valueOf(cmbGender.getValue()), Integer.valueOf(txtContact.getText()), txtDob.getText());
                try {
                    boolean isAdded = DoctorModel.addNewDoctor(doctor);
                    isAddSuccess(isAdded);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "Receptionist" :
                Receptionist receptionist = new Receptionist(txtId.getText(), txtName.getText(), txtAddress.getText(), String.valueOf(cmbGender.getValue()), Integer.valueOf(txtContact.getText()), txtDob.getText());
                try {
                    boolean isAdded = ReceptionistModel.addNewReceptionist(receptionist);
                    isAddSuccess(isAdded);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "Patient" :
                Patient patient = new Patient(txtId.getText(), txtName.getText(), txtAddress.getText(), String.valueOf(cmbGender.getValue()), Integer.valueOf(txtContact.getText()), txtDob.getText());
                try {
                    boolean isAdded = PatientModel.addNewPatient(patient);
                    isAddSuccess(isAdded);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
        clearFields();
    }

    private void clearFields() {
        cmbRole.getSelectionModel().clearSelection();
        txtId.clear();
        txtName.clear();
        txtSpecialty.clear();
        txtAddress.clear();
        cmbGender.getSelectionModel().clearSelection();
        txtContact.clear();
        txtDob.clear();
        txtCompany.clear();
    }

    public void isAddSuccess(boolean isAdded){
        if (isAdded){
            new Alert(Alert.AlertType.CONFIRMATION,"Added Successful.", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Add Failed..",ButtonType.CLOSE);
        }
    }

    public void cmbRoleOnAction(ActionEvent actionEvent) {
        resetTextFields();
        String role = String.valueOf(cmbRole.getValue());
        switch (role){
            case "Supplier" :
                generateSupplierId();
                txtCompany.setDisable(false);
                txtDob.setDisable(true);
                txtSpecialty.setDisable(true);
                break;
            case "Nurse" :
                generateNurseId();
                txtCompany.setDisable(true);
                txtDob.setDisable(false);
                txtSpecialty.setDisable(true);
                break;
            case "Doctor" :
                generateDoctorId();
                txtCompany.setDisable(true);
                txtDob.setDisable(false);
                txtSpecialty.setDisable(false);
                break;
            case "Receptionist" :
                generateReceptionistId();
                txtCompany.setDisable(true);
                txtDob.setDisable(false);
                txtSpecialty.setDisable(true);
                break;
            case "Patient" :
                generatePatientId();
                txtCompany.setDisable(true);
                txtDob.setDisable(false);
                txtSpecialty.setDisable(true);
                break;
        }
    }

    private void resetTextFields() {
        txtName.clear();
        txtAddress.clear();
        cmbGender.getSelectionModel().clearSelection();
        txtContact.clear();
        txtCompany.clear();
        txtDob.clear();
    }

    private void generatePatientId() {
        try {
            ResultSet rst = PatientModel.getLastId();
            if (rst.next()){
                String lastId = rst.getString(1);
                String[] split = lastId.split("[P]");
                int lastDigits = Integer.valueOf(split[1]);
                lastDigits++;
                String newId = String.format("P%03d", lastDigits);
                txtId.setText(newId);
            }else {
                txtId.setText("P001");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generateReceptionistId() {
        try {
            ResultSet rst = ReceptionistModel.getLastId();
            if (rst.next()){
                String lastId = rst.getString(1);
                String[] split = lastId.split("[R]");
                int lastDigits = Integer.valueOf(split[1]);
                lastDigits++;
                String newId = String.format("R%03d", lastDigits);
                txtId.setText(newId);
            }else {
                txtId.setText("R001");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generateDoctorId() {
        try {
            ResultSet rst = DoctorModel.getLastId();
            if (rst.next()){
                String lastId = rst.getString(1);
                String[] split = lastId.split("[D]");
                int lastDigits = Integer.valueOf(split[1]);
                lastDigits++;
                String newId = String.format("D%03d", lastDigits);
                txtId.setText(newId);
            }else {
                txtId.setText("D001");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generateNurseId() {
        try {
            ResultSet rst = NurseModel.getLastId();
            if (rst.next()){
                String lastId = rst.getString(1);
                String[] split = lastId.split("[N]");
                int lastDigits = Integer.valueOf(split[1]);
                lastDigits++;
                String newId = String.format("N%03d", lastDigits);
                txtId.setText(newId);
            }else {
                txtId.setText("N001");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateSupplierId(){
        try {
            ResultSet rst = SupplierModel.getLastId();
            if (rst.next()){
                String lastId = rst.getString(1);
                String[] split = lastId.split("[S]");
                int lastDigits = Integer.valueOf(split[1]);
                lastDigits++;
                String newId = String.format("S%03d", lastDigits);
                txtId.setText(newId);
            }else {
                txtId.setText("S001");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
