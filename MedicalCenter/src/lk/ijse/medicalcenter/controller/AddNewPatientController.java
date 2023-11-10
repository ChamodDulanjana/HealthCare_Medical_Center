package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medicalcenter.model.PatientModel;
import lk.ijse.medicalcenter.to.Patient;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AddNewPatientController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDob;
    public JFXComboBox cmbGender;
    public JFXButton btnAdd;
    public AnchorPane pane;

    public void initialize(){
        loadlastPatientId();
        loadcmb();
    }

    private void loadcmb() {
        cmbGender.getItems().addAll("Male","Female");
    }

    private void loadlastPatientId() {
        try {
            ResultSet rst = PatientModel.getLastId();
            if (rst.next()){
                String lastId = rst.getString(1);
                String[] split = lastId.split("[P]");
                int lastDigits = Integer.valueOf(split[1]);
                lastDigits++;
                String newId = String.format("P%03d", lastDigits);
                txtId.setText(newId);
            }else {
                txtId.setText("P001");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            boolean isAdded = PatientModel.addNewPatient(new Patient(txtId.getText(), txtName.getText(), txtAddress.getText(), String.valueOf(cmbGender.getValue()), Integer.parseInt(txtContact.getText()), txtDob.getText()));
            if (isAdded){
                btnAdd.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Added Successfully", ok);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(ok) == ok) {
                        try {
                            Navigation.navigate(Routes.Appointment_For_Channeling, pane);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
