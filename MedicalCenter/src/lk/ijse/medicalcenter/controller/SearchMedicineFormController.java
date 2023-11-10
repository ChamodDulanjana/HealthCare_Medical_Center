package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medicalcenter.model.MedicineStoreModel;
import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class SearchMedicineFormController {
    public AnchorPane pane;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtCompany;
    public JFXTextField txtWeight;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;


    public void btnEnterOnAction(MouseEvent mouseEvent) {
        search();
    }

    private void search(){
        txtCode.setEditable(false);
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

    public void txtCoeOnAction(ActionEvent actionEvent) {
        search();
    }
}
