package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.Receptionist;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionistModel {

    public static boolean addNewReceptionist(Receptionist receptionist) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO receptionist VALUES (?,?,?,?,?,?) ";
        return CrudUtil.execute(sql,receptionist.getReceptionistId(),receptionist.getName(),receptionist.getAddress(),receptionist.getGender(),receptionist.getContact(),receptionist.getDob());
    }

    public static ResultSet getLastId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT repId FROM receptionist ORDER BY repId DESC LIMIT 1";
        return CrudUtil.execute(sql);
    }

    public static Receptionist searchReceptionist(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM receptionist WHERE repId = ?";
        ResultSet rst = CrudUtil.execute(sql,id);

        if (rst.next()){
            Receptionist receptionist = new Receptionist(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6));
            return receptionist;
        }
        return null;
    }

    public static boolean updateReceptionist(Receptionist receptionist) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE receptionist SET name = ?, address = ?, gender = ?, contact = ?, dob = ? WHERE repId = ?";
        return CrudUtil.execute(sql,receptionist.getName(),receptionist.getAddress(),receptionist.getGender(),receptionist.getContact(),receptionist.getDob(),receptionist.getReceptionistId());
    }

    public static boolean deleteReceptionist(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM receptionist WHERE repId = ?";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<Receptionist> viewReceptionist() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM receptionist";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Receptionist> receptionistArrayList = new ArrayList<>();
        while (rst.next()){
            Receptionist receptionist = new Receptionist(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6));
            receptionistArrayList.add(receptionist);
        }
        return receptionistArrayList;
    }

    public static String getReceptionistNameUsingId(String empId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM receptionist WHERE repId = ?";
        ResultSet rst = CrudUtil.execute(sql,empId);

        if (rst.next()){
            return rst.getString(1);
        }
        return null;

    }
}
