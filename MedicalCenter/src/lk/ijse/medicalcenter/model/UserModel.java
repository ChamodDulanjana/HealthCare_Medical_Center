package lk.ijse.medicalcenter.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.medicalcenter.to.Nurse;
import lk.ijse.medicalcenter.to.Receptionist;
import lk.ijse.medicalcenter.to.User;
import lk.ijse.medicalcenter.util.CrudUtil;
import lk.ijse.medicalcenter.util.Navigation;
import lk.ijse.medicalcenter.util.Routes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {

    public static ResultSet checkValidation(String userName, String passWord) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE userName =? and passWord =?";
        return CrudUtil.execute(sql,userName,passWord);
    }

    public static ArrayList<String> getEmployeeIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT empId FROM user ";
        ResultSet rst = CrudUtil.execute(sql);
        ArrayList<String > empIdArrayList = new ArrayList<>();
        while (rst.next()){
            empIdArrayList.add(rst.getString(1));
        }
        return empIdArrayList;
    }


    public static boolean signUp(User user) throws SQLException, ClassNotFoundException {
        String sql4 = "SELECT * FROM  user WHERE empId = ?";
        ResultSet rst4 = CrudUtil.execute(sql4,user.getEmployeeId());
        if (rst4.next()){
            new Alert(Alert.AlertType.WARNING,"This system Id already exists.",ButtonType.CLOSE).show();
        }else {
            switch (user.getRole()){
                case "Nurse" :
                    String sql = "SELECT * FROM  nurse WHERE nurId = ?";
                    ResultSet rst = CrudUtil.execute(sql,user.getEmployeeId());
                    if (rst.next()){
                        return checkingUser(user);
                    }else {
                        new Alert(Alert.AlertType.CONFIRMATION,"Wrong system Id, Please contact the Administrator.", ButtonType.OK).show();
                    }
                    break;
                case "Receptionist" :
                    String sql2 = "SELECT * FROM  receptionist WHERE repId = ?";
                    ResultSet rst2 = CrudUtil.execute(sql2,user.getEmployeeId());
                    if (rst2.next()){
                       return checkingUser(user);
                    }else {
                        new Alert(Alert.AlertType.CONFIRMATION,"Wrong system Id, Please contact the Administrator.", ButtonType.OK).show();
                    }
                    break;

            }
        }

        return false;
    }

    public static boolean checkingUser(User user) throws SQLException, ClassNotFoundException {
        String sql5 = "SELECT * FROM user WHERE userName =? and passWord =?";
        ResultSet rst5 = CrudUtil.execute(sql5,user.getUserName(),user.getPassword());
        if (rst5.next()){
            new Alert(Alert.AlertType.WARNING,"Try another UserName or Password",ButtonType.CLOSE).show();
        }else {
            String sql1 = "INSERT INTO user VALUES (?,?,?,?) ";
            return CrudUtil.execute(sql1,user.getEmployeeId(),user.getRole(),user.getUserName(),user.getPassword());
        }
        return false;
    }


}
