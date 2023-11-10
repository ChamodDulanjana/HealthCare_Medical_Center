package lk.ijse.medicalcenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.DoctorModel;
import lk.ijse.medicalcenter.model.NurseModel;
import lk.ijse.medicalcenter.to.Doctor;
import lk.ijse.medicalcenter.to.Nurse;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NurseFormController {
    public AnchorPane fullPane;
    public AnchorPane paneOne;
    public AnchorPane paneTwo;
    public Text txtId;
    public Text txtName;
    public Text txtRole;
    public AnchorPane paneOne1;
    public Text txtDate;
    public Text txtTime;
    public TableView tblDoctor;
    public TableColumn colDoctorId;
    public TableColumn colDoctorName;
    public TableColumn colNurseId;
    public TableColumn colNurseName;
    public TableView tblNurse;

    public void initialize(){
        txtDate.setText(ClassGetData.loadDate());
        ClassGetData.setTime(txtTime);
        loadProfile();
        loadNurseTable();
        loadDoctorTable();
    }

    private void loadProfile() {
        try {
            String name = NurseModel.getNurseNameUsingId(ClassGetData.empId);
            System.out.println(name);
            txtName.setText(name);
            txtId.setText(ClassGetData.empId);
            txtRole.setText("Nurse");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void paneDashboardOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Dashboard, paneTwo);
    }

    public void paneMakeAPaymentOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Issue_Bill, paneTwo);
    }

    public void paneTestingOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Test_Form, paneTwo);
    }

    public void paneLogOutOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Sign_In, fullPane);
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
