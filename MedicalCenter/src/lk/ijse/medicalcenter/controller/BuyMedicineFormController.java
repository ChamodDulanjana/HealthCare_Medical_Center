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
import lk.ijse.medicalcenter.to.*;
import lk.ijse.medicalcenter.view.tm.BuyMedicineTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class BuyMedicineFormController {
    public Text txtMedicinePayId;
    public Text txtDate;
    public static String code;
    public Text txtCompany;
    public TableView tblBuyMedicine;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colCompany;
    public TableColumn colWeight;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Text txtNetTotal;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public Text txtDescription;
    public Text txtWeight;
    public JFXButton btnRemove;
    public JFXComboBox cmbSupplierName;
    public static String supplierId;
    public Text txtQtyWarning;
    public JFXButton btnPlaceOrder;
    public Text txtSuppliersCompany;
    public JFXComboBox cmbDescription;
    private Pattern qtyPattern;

    private ObservableList<BuyMedicineTM> observableList = FXCollections.observableArrayList();

    public void initialize(){
        loadNextMedicinePaymentId();
        txtDate.setText(ClassGetData.loadDate());
        loadSupplierNames();
        loadMedicineNames();
        setCellValueFactory();
        txtQtyWarning.setVisible(false);
        qtyPattern = Pattern.compile("^[0-9]{1,}$");
        btnPlaceOrder.setDisable(true);
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colCompany.setCellValueFactory(new PropertyValueFactory("company"));
        colWeight.setCellValueFactory(new PropertyValueFactory("weight"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
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

    private void loadSupplierNames() {
        try {
            ArrayList<String> supplierNames = SupplierModel.getSupplierNames();
            ObservableList<String> observableList = FXCollections.observableArrayList(supplierNames);
            cmbSupplierName.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadNextMedicinePaymentId() {
        try {
            String lastMedicineOrderId = MedicinePaymentModel.generateNextMedicinePaymentId();
            txtMedicinePayId.setText(lastMedicineOrderId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        boolean isQtyMatched = qtyPattern.matcher(txtQty.getText()).matches();
        if (cmbSupplierName.getValue() != null){
            if (cmbDescription.getValue() != null){
                if (isQtyMatched){
                    btnAddToCartOperation();
                }else {
                    txtQtyWarning.setVisible(true);
                    txtQty.requestFocus();
                }
            }else {
                cmbDescription.setFocusColor(Paint.valueOf("Red"));
                cmbDescription.requestFocus();
            }
        }else {
            cmbSupplierName.setFocusColor(Paint.valueOf("Red"));
            cmbSupplierName.requestFocus();
        }
    }

    private void btnAddToCartOperation() {
        txtQtyWarning.setVisible(false);
        String description = String.valueOf(cmbDescription.getValue());
        String company = txtCompany.getText();
        String weight = txtWeight.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = qty*unitPrice;

        txtQty.clear();

        if (!observableList.isEmpty()){
            for (int i=0; i<tblBuyMedicine.getItems().size(); i++){
                if (colCode.getCellData(i).equals(code)){
                    qty += (int) colQty.getCellData(i);
                    total = qty*unitPrice;

                    observableList.get(i).setQty(qty);
                    observableList.get(i).setTotal(total);
                    tblBuyMedicine.refresh();
                    setNetTotal();
                    return;
                }
            }
        }
        observableList.add(new BuyMedicineTM(code,description,company,weight,qty,unitPrice,total));
        tblBuyMedicine.setItems(observableList);
        setNetTotal();
        txtQty.requestFocus();
        btnPlaceOrder.setDisable(false);
    }

    private void setNetTotal() {
        double total = 0;
        for (int i=0; i<tblBuyMedicine.getItems().size(); i++){
            total += (double) colTotal.getCellData(i);
        }
        txtNetTotal.setText(String.valueOf(total));
    }

    public void btnRemoveCartOnAction(ActionEvent actionEvent) {
        btnRemove.setOnAction((event) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure ?",ok,no);
            Optional<ButtonType> rst = alert.showAndWait();

            if (rst.orElse(no) == ok){
                tblBuyMedicine.getSelectionModel().getSelectedItem();
                tblBuyMedicine.getItems().removeAll(tblBuyMedicine.getSelectionModel().getSelectedItem());
                setNetTotal();
            }
        });

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String medicinePayId = txtMedicinePayId.getText();
        String date = txtDate.getText();

        ArrayList<StoreDetail> storeDetailsList = new ArrayList<>();

        for (int i=0; i<tblBuyMedicine.getItems().size(); i++){
            BuyMedicineTM tm = observableList.get(i);
            storeDetailsList.add(new StoreDetail(medicinePayId,tm.getCode(),tm.getDescription(),tm.getCompany(),tm.getWeight(),tm.getUnitPrice(),tm.getQty()));
        }
        PlaceMedicineOrder placeMedicineOrder = new PlaceMedicineOrder(medicinePayId, supplierId, storeDetailsList);
        try {
            boolean isPlaced = PlaceMedicineOrderModel.placeOrder(placeMedicineOrder);
            if (isPlaced){
                observableList.clear();
                loadNextMedicinePaymentId();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
                saveMedicineExpensesData();
                btnPlaceOrder.setDisable(true);
                loadNextMedicinePaymentId();
                clearField();
            }else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveMedicineExpensesData() {
        try {
        boolean isAdded = MedicineExpensesModel.saveMedicineExpenses(new MedicineExpenses(txtMedicinePayId.getText(), txtDate.getText(), Double.parseDouble(txtNetTotal.getText())));
            if (isAdded){
                //
            }else {
                new Alert(Alert.AlertType.WARNING,"The Medicine Expenses didn't Update!",ButtonType.CLOSE).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearField() {
        cmbSupplierName.getSelectionModel().clearSelection();
        txtSuppliersCompany.setText("");
        //cmbDescription.getSelectionModel().clearSelection();
        txtCompany.setText("");
        txtWeight.setText("");
        txtUnitPrice.setText("");
        txtNetTotal.setText("00.0");
    }

    public void cmbSupplierNameOnAction(ActionEvent actionEvent) {
        String supplierName = String.valueOf(cmbSupplierName.getValue());
        try {
            String supplierCompany = SupplierModel.getSupplierCompany(supplierName);
            txtSuppliersCompany.setText(supplierCompany);
            supplierId = SupplierModel.getSupplierId(supplierName);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cmbDescriptionOnAction(ActionEvent actionEvent) {
        try {
            MedicineStore medicineStore = MedicineStoreModel.SearchMedicineUsingName(String.valueOf(cmbDescription.getValue()));
            code = medicineStore.getCode();
            txtCompany.setText(medicineStore.getCompany());
            txtWeight.setText(medicineStore.getWeight());
            txtUnitPrice.setText(String.valueOf(medicineStore.getUnitPrice()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtQty.requestFocus();
    }
}














