package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.*;
import lk.ijse.medicalcenter.to.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewDetailFormController {
    public JFXComboBox cmbRole;
    public TableView tblPRN;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colGender;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableView tblSupplier;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colSupCompany;
    public TableColumn colSupAddress;
    public TableColumn colSupGender;
    public TableColumn colSupContact;
    public TableColumn colAddress11;
    public TableView tblDoctor;
    public TableColumn colDocId;
    public TableColumn colDocName;
    public TableColumn colDocSpecialty;
    public TableColumn colDocAddress;
    public TableColumn colDocGender;
    public TableColumn colDocContact;
    public TableColumn colDocDob;

    public void initialize(){
        ClassGetData.loadRole(cmbRole);
        cmbRole.getSelectionModel().selectFirst();
        cmbRoleOperation();
    }

    public void cmbRoleOnAction(ActionEvent actionEvent) {
        cmbRoleOperation();
    }

    private void cmbRoleOperation(){
        String role = String.valueOf(cmbRole.getValue());

        switch (role){
            case "Receptionist" :
                tblSupplier.setVisible(false);
                tblDoctor.setVisible(false);
                tblPRN.setVisible(true);
                try {
                    ArrayList<Receptionist> receptionistArrayList = ReceptionistModel.viewReceptionist();
                    ObservableList<Receptionist> receptionistObservableList = FXCollections.observableArrayList(receptionistArrayList);

                    tblPRN.setItems(receptionistObservableList);
                    setDataToTblColumns(role);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "Nurse" :
                tblSupplier.setVisible(false);
                tblDoctor.setVisible(false);
                tblPRN.setVisible(true);
                try {
                    ArrayList<Nurse> nurseArrayList = NurseModel.viewNurse();
                    ObservableList<Nurse> nurseObservableList = FXCollections.observableArrayList(nurseArrayList);
                    tblPRN.setItems(nurseObservableList);

                    setDataToTblColumns(role);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
            case "Patient" :
                tblSupplier.setVisible(false);
                tblDoctor.setVisible(false);
                tblPRN.setVisible(true);
                try {
                    ArrayList<Patient> patientArrayList = PatientModel.viewPatient();
                    ObservableList<Patient> patientObservableList = FXCollections.observableArrayList(patientArrayList);
                    tblPRN.setItems(patientObservableList);

                    setDataToTblColumns(role);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "Doctor" :
                tblPRN.setVisible(false);
                tblSupplier.setVisible(false);
                tblDoctor.setVisible(true);
                try {
                    ArrayList<Doctor> doctorArrayList = DoctorModel.viewDoctor();
                    ObservableList<Doctor> doctorObservableList = FXCollections.observableArrayList(doctorArrayList);

                    tblDoctor.setItems(doctorObservableList);
                    setDataToTblColumns(role);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
            case "Supplier" :
                tblPRN.setVisible(false);
                tblDoctor.setVisible(false);
                tblSupplier.setVisible(true);
                try {
                    ArrayList<Supplier> supplierArrayList = SupplierModel.viewSupplier();
                    ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList(supplierArrayList);

                    tblSupplier.setItems(supplierObservableList);
                    setDataToTblColumns(role);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    private void setDataToTblColumns(String role) {
        switch (role) {
            case "Receptionist":
                colId.setCellValueFactory(new PropertyValueFactory<>("receptionistId"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
                colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
                break;
            case "Nurse":
                colId.setCellValueFactory(new PropertyValueFactory<>("nurseId"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
                colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
                break;
            case "Patient":
                colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
                colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
                break;
            case "Doctor":
                colDocId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
                colDocName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colDocSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
                colDocAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colDocGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                colDocContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
                colDocDob.setCellValueFactory(new PropertyValueFactory<>("dob"));

                break;
            case "Supplier":
                colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
                colSupName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colSupCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
                colSupAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colSupGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                colSupContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }

    }
}
