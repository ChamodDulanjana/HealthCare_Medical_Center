package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.istack.internal.NotNull;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.*;
import lk.ijse.medicalcenter.to.Income;
import lk.ijse.medicalcenter.to.Test;
import lk.ijse.medicalcenter.to.TestPayment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

public class TestFormController {

    public JFXComboBox cmbDescription;
    public Text txtPaymentId;
    public Text txtDate;
    public JFXTextField txtContactNo;
    public Text txtPatientName;
    public Text txtTestId;
    public Text txtNurseId;
    public static String patientId;
    public JFXButton btnIssueBill;
    public JFXTextField txtTotalFee;
    private Pattern mobilePattern;
    private Pattern totalFeePattern;

    public void initialize(){
        loadPaymentId();
        txtDate.setText(ClassGetData.loadDate());
        txtNurseId.setText(ClassGetData.empId);
        loadTestId();
        loadcmb();
        mobilePattern = Pattern.compile("^(075|077|072|076|078|071|011|070)([0-9]{7})$");
        totalFeePattern = Pattern.compile("^[0-9].{1,}$");
    }

    private void loadcmb() {
        cmbDescription.getItems().addAll("Blood","Urine");
    }

    private void loadTestId() {
        try {
            String lastTestId = TestModel.generateNextTestId();
            txtTestId.setText(lastTestId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadPaymentId() {
        try {
            String lastPayId = PaymentModel.generateNextPayId();
            txtPaymentId.setText(lastPayId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnEnterOnAction(MouseEvent mouseEvent) {
        try {
            String name  = PatientModel.searchPatientUsingContact(txtContactNo.getText());
            patientId = PatientModel.getPatientId(txtContactNo.getText());
            if (name==null){
                new Alert(Alert.AlertType.WARNING,"Wrong Contact Number !", ButtonType.CLOSE).show();
            }else {
                txtPatientName.setText(name);
                cmbDescription.getSelectionModel().clearSelection();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnIssueBillOnAction(ActionEvent actionEvent) {
        boolean isMobileMatched = mobilePattern.matcher(txtContactNo.getText()).matches();
        boolean isTotalFeeMatched = totalFeePattern.matcher(txtTotalFee.getText()).matches();
        if (isMobileMatched){
            if (cmbDescription.getValue() != null){
                if (isTotalFeeMatched){
                    btnIssueBillOperation();
                }else {
                    txtTotalFee.setFocusColor(Paint.valueOf("Red"));
                    txtTotalFee.requestFocus();
                }
            }else {
                cmbDescription.setFocusColor(Paint.valueOf("Red"));
                cmbDescription.requestFocus();
            }
        }else {
            txtContactNo.setFocusColor(Paint.valueOf("Red"));
            txtContactNo.requestFocus();
        }
    }

    private void btnIssueBillOperation() {
        ArrayList<Test> testArrayList = new ArrayList<>();
        testArrayList.add(new Test(txtTestId.getText(),String.valueOf(cmbDescription.getValue()),Double.parseDouble(txtTotalFee.getText()),txtPaymentId.getText()));

        TestPayment testPayment = new TestPayment(txtPaymentId.getText(), LocalDate.now(), patientId, txtNurseId.getText(), testArrayList);

        try {
            boolean isSaved = TestPaymentModel.issueBill(testPayment);
            if (isSaved){
                loadPaymentId();
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Successfully").show();
                saveIncomeData();
                clearFields();
                loadTestId();
            }else {
                new Alert(Alert.AlertType.ERROR, "Payment failed").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtContactNo.clear();
        txtPatientName.setText("");
        cmbDescription.getSelectionModel().clearSelection();
    }

    public void cmbDescriptionOnAction(ActionEvent actionEvent) {
    }

    private void saveIncomeData() {
        Calendar cal = Calendar.getInstance();
        try {
            boolean isAdded = IncomeModel.saveIncomeData(new Income(txtPaymentId.getText(), String.valueOf(cal.get(Calendar.DATE)), String.valueOf(cal.get(Calendar.MONTH)+1), String.valueOf(cal.get(Calendar.YEAR)),Double.parseDouble(txtTotalFee.getText())));
            if (isAdded){
                System.out.println(String.valueOf(cal.get(Calendar.DATE)));
                System.out.println(String.valueOf(cal.get(Calendar.MONTH)+1));
                System.out.println(String.valueOf(cal.get(Calendar.YEAR)));
            }else {
                new Alert(Alert.AlertType.WARNING,"The Income didn't Update!",ButtonType.CLOSE).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
