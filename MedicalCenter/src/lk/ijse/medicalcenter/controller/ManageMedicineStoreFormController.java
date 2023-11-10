package lk.ijse.medicalcenter.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;

public class ManageMedicineStoreFormController {
    public AnchorPane pane;

    public void paneAddNewMedicineOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Add_Medicine_Form, pane);
    }

    public void paneViewMedicineDetailsOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.View_Medicine_Form, pane);
    }

    public void paneDeleteMedicineOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Delete_Medicine_Form, pane);
    }

    public void paneUpdateMedicineOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Update_Medicine_Form, pane);
    }

    public void paneSearchMedicineOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Search_Medicine_Form, pane);
    }
}
