package lk.ijse.medicalcenter.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.medicalcenter.db.ClassGetData;
import lk.ijse.medicalcenter.model.UserModel;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.regex.Pattern;

public class SignInFormController {
    public AnchorPane pane;
    public JFXTextField txtUserName;
    public JFXPasswordField txtHidePassword;
    public JFXTextField txtShowPassword;
    public ImageView imgShow;
    public ImageView imgHide;
    private Pattern userNamePattern;
    private Pattern passWordPattern;

    public void initialize(){
        txtShowPassword.setVisible(false);
        imgHide.setVisible(false);
        userNamePattern = Pattern.compile("^[a-z0-9]{4,}$");
        passWordPattern = Pattern.compile("^[a-zA-Z0-9_]{8,}$");
    }

    public void signUpOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Sign_Up, pane);
    }

    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException {
        boolean isUserNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        boolean isPassWordMatched = passWordPattern.matcher(txtHidePassword.getText()).matches();
        if (isUserNameMatched){
            if (isPassWordMatched){
                btnSignInOperation();
            }else {
                txtHidePassword.setFocusColor(Paint.valueOf("Red"));
                txtHidePassword.requestFocus();
                txtShowPassword.setFocusColor(Paint.valueOf("Red"));
                txtShowPassword.requestFocus();
            }
        }else {
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            txtUserName.requestFocus();
        }
    }

    private void btnSignInOperation() {
        ResultSet rst;
        try {
                rst = UserModel.checkValidation(txtUserName.getText(), txtHidePassword.getText());
                if (rst.next()){
                String role = rst.getString(2);
                ClassGetData.role = role;
                ClassGetData.empId = rst.getString(1);
                checkRole(role);
            }else {
                new Alert(Alert.AlertType.WARNING,"Wrong Username or Password!", ButtonType.CLOSE).show();

            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void checkRole(String role) throws IOException {
        switch (role){
            case "admin" :
            case "Admin" :
                Navigation.navigate(Routes.Admin_Form, pane);
                break;
            case "nurse":
            case "Nurse":
                Navigation.navigate(Routes.Nurse_Form, pane);
                break;
            case "receptionist" :
            case "Receptionist" :
                Navigation.navigate(Routes.Receptionist_Form, pane);
                break;

        }
    }


    public void imgHideOnAction(MouseEvent mouseEvent) {
        imgHide.setVisible(false);
        imgShow.setVisible(true);
        txtShowPassword.setVisible(false);
        txtHidePassword.setVisible(true);
    }

    public void imgShowOnAction(MouseEvent mouseEvent) {
        imgShow.setVisible(false);
        imgHide.setVisible(true);
        txtHidePassword.setVisible(false);
        txtShowPassword.setVisible(true);
        txtShowPassword.setText(txtHidePassword.getText());

    }
}
