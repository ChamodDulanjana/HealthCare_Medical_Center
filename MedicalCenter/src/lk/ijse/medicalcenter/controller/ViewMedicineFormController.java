package lk.ijse.medicalcenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medicalcenter.model.MedicineStoreModel;
import lk.ijse.medicalcenter.report.report_connection.ReportConnection;
import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewMedicineFormController {
    public AnchorPane pane;
    public TableView<MedicineStore> tblViewMedicine;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colCompany;
    public TableColumn colWeight;

    public void initialize(){
        loadViewMedicineForm();
    }

    private void loadViewMedicineForm() {
        try {
            ArrayList<MedicineStore> medicineStoreArrayList = MedicineStoreModel.viewMedicineModel();
            ObservableList<MedicineStore> medicineStoreObservableList = FXCollections.observableArrayList(medicineStoreArrayList);

            tblViewMedicine.setItems(medicineStoreObservableList);
            setDataToColumns();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDataToColumns() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
    }

    public void btnGenerateReportOnAction(ActionEvent actionEvent) {
        ReportConnection.reportConnection("medicine_store.jrxml");
    }
}
