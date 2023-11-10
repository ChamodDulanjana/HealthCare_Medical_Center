package lk.ijse.medicalcenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.medicalcenter.model.AppointmentModel;
import lk.ijse.medicalcenter.to.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewAppointmentsController {


    public TableView tblAppointment;
    public TableColumn colAAppId;
    public TableColumn colDate;
    public TableColumn colAppType;
    public TableColumn colTest;
    public TableColumn colDoctor;
    public TableColumn colChannTime;
    public TableColumn colChannDate;
    public TableColumn colPatientId;
    public TableColumn colReceptionistId;

    public void initialize(){
        loadTable();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colAAppId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAppType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        colTest.setCellValueFactory(new PropertyValueFactory<>("test"));
        colReceptionistId.setCellValueFactory(new PropertyValueFactory<>("receptionistId"));
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colChannTime.setCellValueFactory(new PropertyValueFactory<>("channelingTime"));
        colChannDate.setCellValueFactory(new PropertyValueFactory<>("channelingDate"));

    }

    private void loadTable() {
        try {
            ArrayList<Appointment> appointmentslist = AppointmentModel.viewAppointments();
            ObservableList<Appointment> observableList = FXCollections.observableArrayList(appointmentslist);
            tblAppointment.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
