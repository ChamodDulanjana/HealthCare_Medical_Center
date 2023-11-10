package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medicalcenter.model.MedicineStoreModel;
import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateMedicineFormController {
    public AnchorPane pane;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtCompany;
    public JFXTextField txtWeight;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public Button btnUpdate;

    public void btnEnterOnAction(MouseEvent mouseEvent) {
        txtCode.setEditable(false);
        txtWeight.setEditable(false);
        try {
            MedicineStore medicineStore = MedicineStoreModel.SearchMedicine(txtCode.getText());
            if (medicineStore==null){
                new Alert(Alert.AlertType.WARNING,"Medicine not found.", ButtonType.CLOSE).show();
            }else {
                txtDescription.setText(medicineStore.getDescription());
                txtCompany.setText(medicineStore.getCompany());
                txtWeight.setText(medicineStore.getWeight());
                txtUnitPrice.setText(String.valueOf(medicineStore.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(medicineStore.getQtyOnHand()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

//        MedicineStore medicineStore = new MedicineStore();
//        medicineStore.setCode(txtCode.getText());
//        medicineStore.setDescription(txtDescription.getText());
//        medicineStore.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
//        medicineStore.setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));
//        System.out.println(medicineStore.toString());

        String code = txtCode.getText();
        String  description = txtDescription.getText();
        String company = txtCompany.getText();
        String weight = txtWeight.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        MedicineStore medicineStore = new MedicineStore(code, description, company, weight, unitPrice, qtyOnHand);
        try {
            boolean isUpdate = MedicineStoreModel.updateMedicine(medicineStore);
            System.out.println(isUpdate);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Successful",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Update Failed..",ButtonType.CLOSE).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
           //e.printStackTrace();
        }
    }
}
