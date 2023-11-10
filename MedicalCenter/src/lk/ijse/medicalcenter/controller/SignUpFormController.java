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
import lk.ijse.medicalcenter.to.User;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpFormController {
    public AnchorPane pane;
    public JFXComboBox cmbRole;
    public JFXTextField txtEmpId;
    public JFXTextField txtUserName;
    public JFXTextField txtShowPassword;
    public ImageView imgHide;
    public ImageView imgShow;
    public JFXPasswordField txtHidePassWord;
    private Pattern idPattern;
    private Pattern userNamePattern;
    private Pattern passWordPattern;

    public void initialize(){
        txtShowPassword.setVisible(false);
        imgHide.setVisible(false);
        cmbRole.getItems().addAll("Receptionist","Nurse");
        idPattern = Pattern.compile("^[NR0-9]{4}$");
        userNamePattern = Pattern.compile("^[a-z0-9]{4,}$");
        passWordPattern = Pattern.compile("^[a-zA-Z0-9_]{8,}$");
    }

    public void signInOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.Sign_In, pane);
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
        boolean isEmpIdMatched = idPattern.matcher(txtEmpId.getText()).matches();
        boolean isUserNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        boolean isPassWordMatched = passWordPattern.matcher(txtHidePassWord.getText()).matches();
        if (cmbRole.getValue() != null){
            if(isEmpIdMatched){
                if(isUserNameMatched){
                    if (isPassWordMatched){
                        btnSignUpOperation();
                    }else {
                        txtHidePassWord.setFocusColor(Paint.valueOf("Red"));
                        txtHidePassWord.requestFocus();
                        txtShowPassword.setFocusColor(Paint.valueOf("Red"));
                        txtShowPassword.requestFocus();
                    }
                }else {
                    txtUserName.setFocusColor(Paint.valueOf("Red"));
                    txtUserName.requestFocus();
                }
            }else {
                txtEmpId.setFocusColor(Paint.valueOf("Red"));
                txtEmpId.requestFocus();
            }
        }else {
            cmbRole.setFocusColor(Paint.valueOf("Red"));
            cmbRole.requestFocus();
        }
    }

    private void btnSignUpOperation() {
        User user = new User(txtEmpId.getText(), String.valueOf(cmbRole.getValue()), txtUserName.getText(), txtHidePassWord.getText());
        try {
            boolean isAdded = UserModel.signUp(user);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Account Created successfully", ButtonType.OK).show();
                if (String.valueOf(cmbRole.getValue()).equals("Receptionist")){
                    Navigation.navigate(Routes.Receptionist_Form, pane);
                    ClassGetData.role = String.valueOf(cmbRole.getValue());
                    ClassGetData.empId = txtEmpId.getText();
                }else {
                    Navigation.navigate(Routes.Nurse_Form, pane);
                    ClassGetData.role = String.valueOf(cmbRole.getValue());
                    ClassGetData.empId = txtEmpId.getText();
                }
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    public void imgShowOnAction(MouseEvent mouseEvent) {
        imgShow.setVisible(false);
        imgHide.setVisible(true);
        txtHidePassWord.setVisible(false);
        txtShowPassword.setVisible(true);
        txtShowPassword.setText(txtHidePassWord.getText());
    }

    public void imgHideOnAction(MouseEvent mouseEvent) {
        imgHide.setVisible(false);
        imgShow.setVisible(true);
        txtShowPassword.setVisible(false);
        txtHidePassWord.setVisible(true);
    }

}
