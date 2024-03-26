package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.medicalcenter.model.MedicineStoreModel;
import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddNewMedicineFormController {
    public AnchorPane pane;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtCompany;
    public JFXComboBox cmbWeight;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    private Pattern unitPricePattern;
    private Pattern qtyOnHandPattern;

    public void initialize(){
        cmbWeight.getItems().addAll("2mg","5mg","10mg","20mg","50mg","100mg","200mg","250mg","500mg","625mg");
        unitPricePattern = Pattern.compile("^[0-9].{1,}$");
        qtyOnHandPattern = Pattern.compile("^[0-9]{1,}$");
        generateCode();
    }

    private void generateCode() {
        try {
            String lastId = MedicineStoreModel.generateNextCode();
            if (lastId != null){
                String[] split = lastId.split("[M]");
                int lastDigits = Integer.valueOf(split[1]);
                lastDigits++;
                String newId = String.format("M%03d", lastDigits);
                txtCode.setText(newId);
            }else {
                txtCode.setText("M001");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddNewMedicineOnAction(MouseEvent mouseEvent) {
        boolean isUnitPriceMatched = unitPricePattern.matcher(txtUnitPrice.getText()).matches();
        boolean isQtyOnHandMatched = qtyOnHandPattern.matcher(txtQtyOnHand.getText()).matches();
            if (txtDescription.getText().isEmpty() == false){
                if (txtCompany.getText().isEmpty() == false){
                    if (cmbWeight.getValue() != null){
                        if (isUnitPriceMatched){
                            if (isQtyOnHandMatched){
                                btnAddMedicineOperation();
                            }else {
                                txtQtyOnHand.setFocusColor(Paint.valueOf("Red"));
                                txtQtyOnHand.requestFocus();
                            }
                        }else {
                            txtUnitPrice.setFocusColor(Paint.valueOf("Red"));
                            txtUnitPrice.requestFocus();
                        }
                    }else {
                        cmbWeight.setFocusColor(Paint.valueOf("Red"));
                        cmbWeight.requestFocus();
                    }
                }else {
                    txtCompany.setFocusColor(Paint.valueOf("Red"));
                    txtCompany.requestFocus();
                }
            }else {
                txtDescription.setFocusColor(Paint.valueOf("Red"));
                txtDescription.requestFocus();
            }
    }

    private void btnAddMedicineOperation(){
        String code = txtCode.getText();
        String  description = txtDescription.getText();
        String company = txtCompany.getText();
        String weight = String.valueOf(cmbWeight.getValue());
        double unitPrice = Double.parseDouble(String.valueOf(txtUnitPrice.getText()));
        int qtyOnHand = Integer.parseInt(String.valueOf(txtQtyOnHand.getText()));

        MedicineStore medicineStore = new MedicineStore(code, description, company, weight, unitPrice, qtyOnHand);

        try {
            boolean isAdded = MedicineStoreModel.addNewMedicine(medicineStore);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Successful.", ButtonType.OK).show();
                clearField();
                generateCode();
            }else {
                new Alert(Alert.AlertType.WARNING,"Add Failed..",ButtonType.CLOSE).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void clearField() {
        txtDescription.clear();
        txtCompany.clear();
        cmbWeight.getSelectionModel().clearSelection();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }


}
