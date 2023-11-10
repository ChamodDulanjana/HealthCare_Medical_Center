package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.Doctor;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorModel {

    public static ArrayList<Doctor> getDoctorsIdAndName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT docId,name FROM doctor";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Doctor> arrayList = new ArrayList<>();

        while (rst.next()) {
            Doctor doctor = new Doctor(rst.getString(1), rst.getString(2));
            arrayList.add(doctor);

        }
        return arrayList;
    }

    public static boolean addNewDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO doctor VALUES (?,?,?,?,?,?,?) ";
        return CrudUtil.execute(sql,doctor.getDoctorId(),doctor.getName(),doctor.getSpecialty(),doctor.getAddress(),doctor.getGender(),doctor.getContact(),doctor.getDob());
    }

    public static ResultSet getLastId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT docId FROM doctor ORDER BY docId DESC LIMIT 1";
        return CrudUtil.execute(sql);
    }

    public static Doctor searchDoctor(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM doctor WHERE docId = ?";
        ResultSet rst = CrudUtil.execute(sql,id);

        if (rst.next()){
            Doctor doctor = new Doctor(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getInt(6), rst.getString(7));
            return doctor;
        }
        return null;
    }

    public static boolean updateDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE doctor SET name = ?, specialty = ?, address = ?, gender = ?, contact = ?, dob = ? WHERE docId = ?";
        return CrudUtil.execute(sql,doctor.getName(),doctor.getSpecialty(),doctor.getAddress(),doctor.getGender(),doctor.getContact(),doctor.getDob(),doctor.getDoctorId());
    }

    public static boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM doctor WHERE docId = ?";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<Doctor> viewDoctor() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM doctor";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        while (rst.next()){
            Doctor doctor = new Doctor(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getInt(6), rst.getString(7));
            doctorArrayList.add(doctor);
        }
        return doctorArrayList;
    }

    public static String getDoctorSpecialty(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT specialty FROM doctor WHERE name = ?";
        ResultSet rst = CrudUtil.execute(sql,name);

        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
