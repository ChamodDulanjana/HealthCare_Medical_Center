package lk.ijse.medicalcenter.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javafx.util.Duration;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.*;
import lk.ijse.medicalcenter.to.Doctor;
import lk.ijse.medicalcenter.to.Nurse;
import lk.ijse.medicalcenter.to.Patient;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DashboardController {

    public Text txtDate;
    public Text txtTime;
    public TableView tblDoctor;
    public TableView tblNurse;
    public TableColumn colDoctorId;
    public TableColumn colDoctorName;
    public TableColumn colNurseId;
    public TableColumn colNurseName;
    public Text txtId;
    public Text txtName;
    public Text txtRole;

    public void initialize() {
        txtDate.setText(ClassGetData.loadDate());
        ClassGetData.setTime(txtTime);
        loadDoctorTable();
        loadNurseTable();
        loadProfile();
    }

    private void loadProfile() {
        if(ClassGetData.role.equalsIgnoreCase("admin")){
            txtRole.setText("Administrator");
            txtId.setText("A001");
            txtName.setText("Aravinda Kumara");
        }else if (ClassGetData.role.equalsIgnoreCase("nurse")){
            try {
                String name = NurseModel.getNurseNameUsingId(ClassGetData.empId);
                txtName.setText(name);
                txtId.setText(ClassGetData.empId);
                txtRole.setText("Nurse");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if (ClassGetData.role.equalsIgnoreCase("receptionist")){
            try {
                String name = ReceptionistModel.getReceptionistNameUsingId(ClassGetData.empId);
                txtName.setText(name);
                txtId.setText(ClassGetData.empId);
                txtRole.setText("Receptionist");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

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
