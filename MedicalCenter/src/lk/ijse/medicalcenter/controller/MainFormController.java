package lk.ijse.medicalcenter.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;

public class MainFormController {
    public AnchorPane pane;

    public void btnStartOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Sign_In, pane);
    }
}
