package lk.ijse.medicalcenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.medicalcenter.db.DBConnection;
import lk.ijse.medicalcenter.model.IncomeModel;
import lk.ijse.medicalcenter.model.PaymentDetailModel;
import lk.ijse.medicalcenter.report.report_connection.ReportConnection;
import lk.ijse.medicalcenter.to.Income;
import lk.ijse.medicalcenter.to.PaymentDetail;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class ViewIncomeReportsController {

    public TableView tblIncome;
    public TableColumn colDate;
    public TableColumn colAmount;
    public Text txtDate;
    public Text txtDailyIncome;
    public Text txtYear;
    public Text txtAnnualIncome;
    public TableColumn colPaymentId;
    public TableColumn colMonth;
    public TableColumn colYear;
    public Text txtMonth;
    public Text txtMonthlyIncome;

    public void initialize(){
        loadTable();
        setCellValueFactory();
        loadDailyIncome();
        loadAnnualIncome();
        loadMonthlyIncome();
    }

    private void loadMonthlyIncome() {
        Calendar cal = Calendar.getInstance();
        String month = String.valueOf(cal.get(Calendar.MONTH)+1);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        try {
            double total = IncomeModel.calculateMonthlyIncome(month,year);
            txtMonthlyIncome.setText(String.valueOf(total));
            txtMonth.setText(month);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAnnualIncome() {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        try {
            double total = IncomeModel.calculateAnnualIncome(year);
            txtAnnualIncome.setText(String.valueOf(total));
            txtYear.setText(year);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDailyIncome() {
        Calendar cal = Calendar.getInstance();
        String date = String.valueOf(cal.get(Calendar.DATE));
        String month = String.valueOf(cal.get(Calendar.MONTH)+1);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        try {
            double total = IncomeModel.calculateDailyIncome(date,month,year);
            txtDailyIncome.setText(String.valueOf(total));
            txtDate.setText(String.valueOf(LocalDate.now()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colMonth.setCellValueFactory(new PropertyValueFactory("month"));
        colYear.setCellValueFactory(new PropertyValueFactory("year"));
        colAmount.setCellValueFactory(new PropertyValueFactory("amount"));
    }

    private void loadTable() {
        try {
            ArrayList<Income> arrayList = IncomeModel.getIncomes();
            ObservableList<Income> observableList = FXCollections.observableArrayList(arrayList);
            tblIncome.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnGenerateReportOnAction(ActionEvent actionEvent) {
        ReportConnection.reportConnection("income_report.jrxml");
    }
}
