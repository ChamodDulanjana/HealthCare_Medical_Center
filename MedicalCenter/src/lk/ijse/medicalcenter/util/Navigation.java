package lk.ijse.medicalcenter.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case Main_Form:
                window.setTitle("Landing Page");
                initUI("MainForm.fxml");
                break;
            case Sign_In:
                window.setTitle("SignIn Form ");
                initUI("SignInForm.fxml");
                break;
            case Sign_Up:
                window.setTitle("SignUp Form");
                initUI("SignUpForm.fxml");
                break;
            case Receptionist_Form:
                window.setTitle("Receptionist Form");
                initUI("ReceptionistForm.fxml");
                break;
            case Dashboard:
                window.setTitle("Dashboard Form");
                initUI("Dashboard.fxml");
                break;
            case Appointment_For_Channeling:
                window.setTitle("Appointment For Channeling Form");
                initUI("AppoForChanneling.fxml");
                break;
            case Add_Form:
                window.setTitle("Add New Patient Form");
                initUI("AddForm.fxml");
                break;
            case View_Appointment:
                window.setTitle("View Appointment Form");
                initUI("ViewAppointments.fxml");
                break;
            case Admin_Form:
                window.setTitle("Admin Form");
                initUI("AdminForm.fxml");
                break;
            case Manage_Form:
                window.setTitle("Manage Supplier Form");
                initUI("ManageForm.fxml");
                break;
            case Manage_Form_Slider:
                window.setTitle("Manage Slider Form");
                initUI("ManageFormSlider.fxml");
                break;
            case Manage_Medicine_Store:
                window.setTitle("Manage Medicine Store Form");
                initUI("ManageMedicineStoreForm.fxml");
                break;
            case Manage_Medicine_Store_Slider:
                window.setTitle("Manage Medicine Store Slider Form");
                initUI("ManageMedicineStoreSlider.fxml");
                break;
            case Buy_Medicines:
                window.setTitle("Buy Medicines Form");
                initUI("BuyMedicineForm.fxml");
                break;
            case Search_Form:
                window.setTitle("Search Form");
                initUI("SearchForm.fxml");
                break;
            case Update_Form:
                window.setTitle("Update Form");
                initUI("UpdateForm.fxml");
                break;
            case Delete_Form:
                window.setTitle("Delete Form");
                initUI("DeleteForm.fxml");
                break;
            case View_Detail_Form:
                window.setTitle("View Detail Form");
                initUI("ViewDetailForm.fxml");
                break;
            case Add_Medicine_Form:
                window.setTitle("Add Medicine Form");
                initUI("AddNewMedicineForm.fxml");
                break;
            case Search_Medicine_Form:
                window.setTitle("Search Medicine Form");
                initUI("SearchMedicineForm.fxml");
                break;
            case Update_Medicine_Form:
                window.setTitle("Update Medicine Form");
                initUI("UpdateMedicineForm.fxml");
                break;
            case Delete_Medicine_Form:
                window.setTitle("Delete Medicine Form");
                initUI("DeleteMedicineForm.fxml");
                break;
            case View_Medicine_Form:
                window.setTitle("View Medicine Form");
                initUI("ViewMedicineForm.fxml");
                break;
            case Nurse_Form:
                window.setTitle("Nurse Form");
                initUI("NurseForm.fxml");
                break;
            case Issue_Bill:
                window.setTitle("Issue Bill Form");
                initUI("IssueBillsForm.fxml");
                break;
            case Test_Form:
                window.setTitle("Test Form");
                initUI("TestForm.fxml");
                break;
            case Add_New_Patient:
                window.setTitle("Add New Patient Form");
                initUI("AddNewPatient.fxml");
                break;
            case View_Income_Reports:
                window.setTitle("View Income Reports Form");
                initUI("ViewIncomeReports.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/medicalcenter/view/" + location)));
    }
}
