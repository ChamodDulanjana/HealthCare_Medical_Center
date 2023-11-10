package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.db.DBConnection;
import lk.ijse.medicalcenter.model.DoctorModel;
import lk.ijse.medicalcenter.model.NurseModel;
import lk.ijse.medicalcenter.report.report_connection.ReportConnection;
import lk.ijse.medicalcenter.to.Doctor;
import lk.ijse.medicalcenter.to.Nurse;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdminFormController {
    public AnchorPane fullPane;
    public AnchorPane paneTwo;
    public AnchorPane paneThree;
    public AnchorPane paneFour;
    public Text txtDate;
    public Text txtTime;
    public Text txtId;
    public Text txtName;
    public Text txtRole;
    public TableView tblDoctor;
    public TableColumn colDoctorId;
    public TableColumn colDoctorName;
    public TableView tblNurse;
    public TableColumn colNurseId;
    public TableColumn colNurseName;

    public void initialize()  {
        ClassGetData.setTime(txtTime);
        txtDate.setText(ClassGetData.loadDate());
        loadProfile();
        loadDoctorTable();
        loadNurseTable();
    }

    private void loadProfile() {
        txtRole.setText("Administrator");
        txtId.setText("A001");
        txtName.setText("Aravinda Kumara");
    }

    public void paneDashboardOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Dashboard, paneTwo);
    }

    public void paneLogOutOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Sign_In, fullPane);
    }

    public void paneBuyMedicineOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Buy_Medicines, paneTwo);
    }

    public void paneManageMedicineStoreOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Manage_Medicine_Store_Slider, paneTwo);
    }

    public void paneManageOnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Manage_Form_Slider, paneTwo);
    }

    public void paneViewIncomeReportsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.View_Income_Reports, paneTwo);
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

    public void btnViewMedicineExpensesOnAction(ActionEvent actionEvent) {
        ReportConnection.reportConnection("medicine_expenses01.jrxml");
    }
}
