package lk.ijse.medicalcenter.db;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.medicalcenter.model.NurseModel;
import lk.ijse.medicalcenter.to.Nurse;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClassGetData {
    public static String role;
    public static String empId;

    public static void loadRole(JFXComboBox cmbRole){
        cmbRole.getItems().addAll("Receptionist","Nurse","Patient","Doctor","Supplier");
    }
    public static String loadDate() {
        return  (String.valueOf(LocalDate.now()));
    }

    public static void setTime(Text txtTime) {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    txtTime.setText(LocalDateTime.now().format(formatter));

                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }
}
