package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.Nurse;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NurseModel {
    public static ArrayList<Nurse> getNursesIdAndName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT nurId,name FROM nurse";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Nurse> nurseArrayList = new ArrayList<>();

        while (rst.next()){
            Nurse nurse = new Nurse(rst.getString(1), rst.getString(2));
            nurseArrayList.add(nurse);
        }
        return nurseArrayList;
    }

    public static boolean addNewNurse(Nurse nurse) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO nurse VALUES (?,?,?,?,?,?) ";
        return CrudUtil.execute(sql,nurse.getNurseId(),nurse.getName(),nurse.getAddress(),nurse.getGender(),nurse.getContact(),nurse.getDob());
    }

    public static ResultSet getLastId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT nurId FROM nurse ORDER BY nurId DESC LIMIT 1";
        return CrudUtil.execute(sql);
    }

    public static Nurse searchNurse(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM nurse WHERE nurId = ?";
        ResultSet rst = CrudUtil.execute(sql,id);

        if (rst.next()){
            Nurse nurse = new Nurse(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6));
            return nurse;
        }
        return null;
    }

    public static boolean updateNurse(Nurse nurse) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE nurse SET name = ?, address = ?, gender = ?, contact = ?, dob = ? WHERE nurId = ?";
        return CrudUtil.execute(sql,nurse.getName(),nurse.getAddress(),nurse.getGender(),nurse.getContact(),nurse.getDob(),nurse.getNurseId());
    }

    public static boolean deleteNurse(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM nurse WHERE nurId = ?";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<Nurse> viewNurse() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM nurse";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Nurse> nurseArrayList = new ArrayList<>();
        while (rst.next()){
            Nurse nurse = new Nurse(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6));
            nurseArrayList.add(nurse);
        }
        return nurseArrayList;
    }

    public static String getNurseNameUsingId(String empId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM nurse WHERE nurId = ?";
        ResultSet rst = CrudUtil.execute(sql,empId);

        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
