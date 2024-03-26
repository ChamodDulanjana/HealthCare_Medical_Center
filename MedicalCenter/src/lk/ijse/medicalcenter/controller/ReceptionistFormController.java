package lk.ijse.medicalcenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.DoctorModel;
import lk.ijse.medicalcenter.model.NurseModel;
import lk.ijse.medicalcenter.model.ReceptionistModel;
import lk.ijse.medicalcenter.to.Doctor;
import lk.ijse.medicalcenter.to.Nurse;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionistFormController {
    public AnchorPane fullPane;
    public AnchorPane paneTwo;
    public Text txtId;
    public Text txtName;
    public Text txtRole;
    public Text txtDate;
    public Text txtTime;
    public TableView tblDoctor;
    public TableColumn colDoctorId;
    public TableColumn colDoctorName;
    public TableView tblNurse;
    public TableColumn colNurseId;
    public TableColumn colNurseName;

    public void initialize(){
        txtDate.setText(ClassGetData.loadDate());
        ClassGetData.setTime(txtTime);
        loadProfile();
        loadNurseTable();
        loadDoctorTable();
    }

    private void loadProfile() {
        try {
            String name = ReceptionistModel.getReceptionistNameUsingId(ClassGetData.empId);
            txtName.setText(name);
            txtId.setText(ClassGetData.empId);
            txtRole.setText("Receptionist");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void PaneDashboardOnAction(ActionEvent mouseDragEvent) throws IOException {
        Navigation.navigate(Routes.Dashboard, paneTwo);
    }

    public void paneLogOutOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Sign_In, fullPane);
    }

    public void paneAFCOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Appointment_For_Channeling, paneTwo);
    }

    public void paneAddNewPatientOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Add_Form, paneTwo);
    }

    public void ViewAppointmentOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.View_Appointment, paneTwo);
    }

    private void loadNurseTable() {
        try {
            ArrayList<Nurse> nurseArrayList = NurseModel.getNursesIdAndName();
            ObservableList<Nurse> nurseObservableList = FXCollections.observableArrayList(nurseArrayList);
            tblNurse.setItems(nurseObservableList);
            setDataToTableNurseColumns();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDataToTableNurseColumns() {
        colNurseId.setCellValueFactory(new PropertyValueFactory<>("nurseId"));
        colNurseName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    private void loadDoctorTable() {
        try {

            ArrayList<Doctor> arrayList = DoctorModel.getDoctorsIdAndName();
            ObservableList<Doctor> doctorObservableList = FXCollections.observableArrayList(arrayList);
            tblDoctor.setItems(doctorObservableList);

            setDataToTableDoctorColunms();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDataToTableDoctorColunms() {
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

}
