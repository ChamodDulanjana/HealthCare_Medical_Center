package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.Patient;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientModel {
    public static boolean addNewPatient(Patient patient) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO patient VALUES (?,?,?,?,?,?) ";
        return CrudUtil.execute(sql,patient.getPatientId(),patient.getName(),patient.getAddress(),patient.getGender(),patient.getContact(),patient.getDob());
    }

    public static ResultSet getLastId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT patId FROM patient ORDER BY patId DESC LIMIT 1";
        return CrudUtil.execute(sql);
    }

    public static Patient searchPatient(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM patient WHERE patId = ?";
        ResultSet rst = CrudUtil.execute(sql,id);

        if (rst.next()){
            Patient patient = new Patient(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6));
            return patient;
        }
        return null;
    }

    public static boolean updatePatient(Patient patient) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE patient SET name = ?, address = ?, gender = ?, contact = ?, dob = ? WHERE patId = ?";
        return CrudUtil.execute(sql,patient.getName(),patient.getAddress(),patient.getGender(),patient.getContact(),patient.getDob(),patient.getPatientId());
    }

    public static boolean deletePatient(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM patient WHERE patId = ?";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<Patient> viewPatient() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM patient";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Patient> patientArrayList = new ArrayList<>();
        while (rst.next()){
            Patient patient = new Patient(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6));
            patientArrayList.add(patient);
        }
        return patientArrayList;
    }

    public static String searchPatientUsingContact(String contact) throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM patient WHERE contact = ?";
        ResultSet rst = CrudUtil.execute(sql,contact);

        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public static String getPatientId(String contact) throws SQLException, ClassNotFoundException {
        String sql = "SELECT patId FROM patient WHERE contact = ?";
        ResultSet rst = CrudUtil.execute(sql,contact);

        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

}
