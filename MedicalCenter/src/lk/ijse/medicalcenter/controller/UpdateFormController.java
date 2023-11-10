package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import lk.ijse.medicalcenter.model.*;
import lk.ijse.medicalcenter.to.*;

import java.sql.SQLException;

public class UpdateFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtSpecialty;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDob;
    public JFXTextField txtCompany;
    public JFXTextField txtGender;

    public void initialize(){
        
    }

    public void btnEnterOnAction(MouseEvent mouseEvent) {
        String id = txtId.getText();
        txtId.setEditable(false);
        txtDob.setEditable(false);
        if (id.startsWith("R")){
            try {
                Receptionist receptionist = ReceptionistModel.searchReceptionist(id);
                if (receptionist==null){
                    new Alert(Alert.AlertType.WARNING,"Wrong Id.", ButtonType.CLOSE).show();
                }else {
                    txtSpecialty.setDisable(true);
                    txtCompany.setDisable(true);
                    txtDob.setDisable(false);
                    txtSpecialty.clear();
                    txtCompany.clear();
                    txtName.setText(receptionist.getName());
                    txtAddress.setText(receptionist.getAddress());
                    txtGender.setText(receptionist.getGender());
                    txtContact.setText(String.valueOf(receptionist.getContact()));
                    txtDob.setText(receptionist.getDob());

                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (id.startsWith("N")){
            try {
                Nurse nurse = NurseModel.searchNurse(id);
                if (nurse==null){
                    new Alert(Alert.AlertType.WARNING,"Wrong Id.", ButtonType.CLOSE).show();
                }else {
                    txtSpecialty.setDisable(true);
                    txtCompany.setDisable(true);
                    txtDob.setDisable(false);
                    txtSpecialty.clear();
                    txtCompany.clear();
                    txtName.setText(nurse.getName());
                    txtAddress.setText(nurse.getAddress());
                    txtGender.setText(nurse.getGender());
                    txtContact.setText(String.valueOf(nurse.getContact()));
                    txtDob.setText(nurse.getDob());
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (id.startsWith("D")){
            try {
                Doctor doctor = DoctorModel.searchDoctor(id);
                if (doctor==null){
                    new Alert(Alert.AlertType.WARNING,"Wrong Id.", ButtonType.CLOSE).show();
                }else {
                    txtCompany.clear();
                    txtCompany.setDisable(true);
                    txtSpecialty.setDisable(false);
                    txtDob.setDisable(false);
                    txtName.setText(doctor.getName());
                    txtSpecialty.setText(doctor.getSpecialty());
                    txtAddress.setText(doctor.getAddress());
                    txtGender.setText(doctor.getGender());
                    txtContact.setText(String.valueOf(doctor.getContact()));
                    txtDob.setText(doctor.getDob());
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (id.startsWith("P")){
            try {
                Patient patient = PatientModel.searchPatient(id);
                if (patient==null){
                    new Alert(Alert.AlertType.WARNING,"Wrong Id.", ButtonType.CLOSE).show();
                }else {
                    txtSpecialty.setDisable(true);
                    txtCompany.setDisable(true);
                    txtDob.setDisable(false);
                    txtSpecialty.clear();
                    txtCompany.clear();
                    txtName.setText(patient.getName());
                    txtAddress.setText(patient.getAddress());
                    txtGender.setText(patient.getGender());
                    txtContact.setText(String.valueOf(patient.getContact()));
                    txtDob.setText(patient.getDob());

                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (id.startsWith("S")){
            try {
                Supplier supplier = SupplierModel.searchSupplier(id);
                if (supplier==null){
                    new Alert(Alert.AlertType.WARNING,"Wrong Id.", ButtonType.CLOSE).show();
                }else {
                    txtCompany.setDisable(false);
                    txtDob.clear();
                    txtSpecialty.clear();
                    txtDob.setDisable(true);
                    txtSpecialty.setDisable(true);
                    txtName.setText(supplier.getName());
                    txtCompany.setText(supplier.getCompany());
                    txtAddress.setText(supplier.getAddress());
                    txtGender.setText(supplier.getGender());
                    txtContact.setText(String.valueOf(supplier.getContact()));
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        if (id.startsWith("R")){
            Receptionist receptionist = new Receptionist(txtId.getText(), txtName.getText(), txtAddress.getText(), txtGender.getText(), Integer.valueOf(txtContact.getText()), txtDob.getText());
            try {
                boolean isUpdated = ReceptionistModel.updateReceptionist(receptionist);
                isUpdateSuccess(isUpdated);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (id.startsWith("N")){
            Nurse nurse = new Nurse(txtId.getText(), txtName.getText(), txtAddress.getText(), txtGender.getText(), Integer.valueOf(txtContact.getText()), txtDob.getText());
            try {
                boolean isUpdated = NurseModel.updateNurse(nurse);
                isUpdateSuccess(isUpdated);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (id.startsWith("D")){
            Doctor doctor = new Doctor(txtId.getText(), txtName.getText(),txtSpecialty.getText(), txtAddress.getText(), txtGender.getText(), Integer.valueOf(txtContact.getText()), txtDob.getText());
            try {
                boolean isUpdated = DoctorModel.updateDoctor(doctor);
                isUpdateSuccess(isUpdated);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        if (id.startsWith("P")){
            Patient patient = new Patient(txtId.getText(), txtName.getText(), txtAddress.getText(), txtGender.getText(), Integer.valueOf(txtContact.getText()), txtDob.getText());
            try {
                boolean isUpdated = PatientModel.updatePatient(patient);
                isUpdateSuccess(isUpdated);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (id.startsWith("S")){
            Supplier supplier = new Supplier(txtId.getText(), txtName.getText(), txtCompany.getText(), txtAddress.getText(), txtGender.getText(), Integer.valueOf(txtContact.getText()));
            try {
                boolean isUpdated = SupplierModel.updateSupplier(supplier);
                isUpdateSuccess(isUpdated);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void isUpdateSuccess(boolean isUpdated){
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Update Successful.", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Update Failed..",ButtonType.CLOSE);
        }
    }
}
