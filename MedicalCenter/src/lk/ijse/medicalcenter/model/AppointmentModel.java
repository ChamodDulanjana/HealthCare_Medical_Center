package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.Appointment;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentModel {
    public static String generateNextAppId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT appId FROM appointment ORDER BY appId  DESC LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);

        if (rst.next()){
            return generateNextAppId(rst.getString(1));
        }
        return generateNextAppId(rst.getString(null));
    }

    public static String generateNextAppId(String currentAppId){
        if (currentAppId != null){
            String[] split = currentAppId.split("A");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return String.format("A%03d", id);
        }
        return "A001";
    }

    public static ArrayList<Appointment> viewAppointments() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM appointment";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        while (rst.next()){
            appointmentsList.add(new Appointment(rst.getString(1),rst.getString(2),rst.getString(9),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(8),rst.getString(6),rst.getString(7)));
        }
        return appointmentsList;
    }

    public static boolean addNewAppointment(Appointment app) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO appointment VALUES(?,?,?,?,?,?,?,?,?) ";
        return CrudUtil.execute(sql,app.getAppointmentId(),app.getDate(),app.getAppointmentType(),app.getTest(),app.getDoctor(),app.getChannelingTime(),app.getChannelingDate(),app.getPatientId(),app.getReceptionistId());
    }

    public static Appointment getLastAppoDateAndTime(String pastAppId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT chanTime,chanDate FROM appointment WHERE appId = ?";
        ResultSet rst = CrudUtil.execute(sql,pastAppId);

        if (rst.next()){
            return new Appointment(rst.getString(1),rst.getString(2) );
        }
        return null;
    }
}
