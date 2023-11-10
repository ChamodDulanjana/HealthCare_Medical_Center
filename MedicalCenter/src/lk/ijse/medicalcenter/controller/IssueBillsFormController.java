package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.*;
import lk.ijse.medicalcenter.report.report_connection.ReportConnection;
import lk.ijse.medicalcenter.to.Income;
import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.to.PaymentDetail;
import lk.ijse.medicalcenter.to.PlaceOrder;
import lk.ijse.medicalcenter.view.tm.IssueBillTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.regex.Pattern;

public class IssueBillsFormController {
    public Text txtPayId;
    public Text txtDate;
    public static String code;
    public Text txtDescription;
    public Text txtQtyOnHand;
    public TableView tblPayment;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colWeight;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Text txtNetTotal;
    public TextField txtQty;
    public Text txtUnitPrice;
    public Text txtNurseId;
    public JFXButton btnRemove;
    public Text txtWeight;
    public Text txtPatientName;
    public JFXButton btnEnter;
    public JFXTextField txtContactNo;
    public JFXButton btnIsssueBill;
    public Text txtQtyWarning;
    public JFXComboBox cmbDescription;
    private ObservableList<IssueBillTM> observableList = FXCollections.observableArrayList();
    public static String patientId;
    private Pattern mobilePattern;
    private Pattern qtyPattern;

    public void initialize(){
        loadPaymentId();
        txtDate.setText(ClassGetData.loadDate());
        loadNurseId();
        loadMedicineNames();
        setCellValueFactory();
        mobilePattern = Pattern.compile("^(075|077|072|076|078|071|011|070)([0-9]{7})$");
        qtyPattern = Pattern.compile("^[0-9]{1,}$");
        txtQtyWarning.setVisible(false);
        btnIsssueBill.setDisable(true);
    }

    private void loadMedicineNames() {
        try {
            ArrayList<String> arrayList = MedicineStoreModel.getMedicineNames();
            ObservableList<String> observableList = FXCollections.observableArrayList(arrayList);
            cmbDescription.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveIncomeData() {
        Calendar cal = Calendar.getInstance();
        try {
        boolean isAdded = IncomeModel.saveIncomeData(new Income(txtPayId.getText(), String.valueOf(cal.get(Calendar.DATE)), String.valueOf(cal.get(Calendar.MONTH)+1), String.valueOf(cal.get(Calendar.YEAR)),Double.parseDouble(txtNetTotal.getText())));
        if (isAdded){
            //
        }else {
            new Alert(Alert.AlertType.WARNING,"The Income didn't Update!",ButtonType.CLOSE).show();
        }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadNurseId() {
        txtNurseId.setText(ClassGetData.empId);
    }

    private void loadPaymentId() {
        try {
            String lastPayId = PaymentModel.generateNextPayId();
            txtPayId.setText(lastPayId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddToBillOnAction(ActionEvent actionEvent) {
        boolean isMobileMatched = mobilePattern.matcher(txtContactNo.getText()).matches();
        boolean isQtyMatched = qtyPattern.matcher(txtQty.getText()).matches();
        if (isMobileMatched){
            if (cmbDescription.getValue() != null){
                if (isQtyMatched){
                    txtQtyWarning.setVisible(false);
                    btnAddToBillOperation();
                }else {
                    txtQtyWarning.setVisible(true);
                    txtQty.requestFocus();
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

    private void btnAddToBillOperation() {
        String description = String.valueOf(cmbDescription.getValue());
        String weight = txtWeight.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = qty*unitPrice;

        txtQty.clear();

        if (!observableList.isEmpty()){
            for (int i=0; i<tblPayment.getItems().size(); i++){
                if (colCode.getCellData(i).equals(code)){
                    qty += (int) colQty.getCellData(i);
                    total = qty*unitPrice;

                    observableList.get(i).setQty(qty);
                    observableList.get(i).setTotal(total);
                    tblPayment.refresh();
                    setNetTotal();
                    return;
                }
            }
        }
        observableList.add(new IssueBillTM(code,description,weight,qty,unitPrice,total));
        tblPayment.setItems(observableList);
        setNetTotal();
        btnIsssueBill.setDisable(false);
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        btnRemove.setOnAction((event) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure ?",ok,no);
            Optional<ButtonType> rst = alert.showAndWait();

            if (rst.orElse(no) == ok){
                tblPayment.getSelectionModel().getSelectedItem();
                tblPayment.getItems().removeAll(tblPayment.getSelectionModel().getSelectedItem());
                setNetTotal();
            }
        });
    }

    private void setNetTotal() {
        double total = 0;
        for (int i=0; i<tblPayment.getItems().size(); i++){
            total += (double) colTotal.getCellData(i);
        }
        txtNetTotal.setText(String.valueOf(total));
    }

    public void btnIssueBillOnAction(ActionEvent actionEvent) {
        String paymentId = txtPayId.getText();
        String nurseId = txtNurseId.getText();

        ArrayList<PaymentDetail> paymentDetailsList = new ArrayList<>();

        for (int i=0; i<tblPayment.getItems().size(); i++){
            IssueBillTM tm = observableList.get(i);
            paymentDetailsList.add(new PaymentDetail(paymentId,tm.getCode(),tm.getDescription(),tm.getWeight(),tm.getUnitPrice(),tm.getQty()));
        }

        PlaceOrder placeOrder = new PlaceOrder(paymentId, patientId, nurseId, paymentDetailsList);

        try {
            boolean isIssued = PlaceOrderModel.placeOrder(placeOrder);
            if (isIssued){
                observableList.clear();
                loadPaymentId();
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Successfully").show();
                saveIncomeData();
                btnIsssueBill.setDisable(true);
                clearFields();
                loadPaymentId();
                ReportConnection.reportConnection("Invoice02.jrxml");
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
        //cmbDescription.getSelectionModel().clearSelection();
        txtWeight.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
        txtNetTotal.setText("00.0");
    }

    public void btnEnterOnAction(ActionEvent mouseEvent) {
        try {
            String name  = PatientModel.searchPatientUsingContact(txtContactNo.getText());
            patientId = PatientModel.getPatientId(txtContactNo.getText());
            if (name==null){
                new Alert(Alert.AlertType.WARNING,"Wrong Contact Number !", ButtonType.CLOSE).show();
            }else {
                txtPatientName.setText(name);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colWeight.setCellValueFactory(new PropertyValueFactory("weight"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
    }

    public void cmbDescriptionOnAction(ActionEvent actionEvent) {
        try {
            MedicineStore medicineStore = MedicineStoreModel.SearchMedicineUsingName(String.valueOf(cmbDescription.getValue()));
            code = medicineStore.getCode();
            txtWeight.setText(medicineStore.getWeight());
            txtUnitPrice.setText(String.valueOf(medicineStore.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(medicineStore.getQtyOnHand()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(txtQtyOnHand.getText())<20){
            new Alert(Alert.AlertType.WARNING,"This item is running low", ButtonType.OK).show();
        }
    }
}
