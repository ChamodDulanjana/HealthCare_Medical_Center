package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.Income;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeModel {
    public static ArrayList<Income> getIncomes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM income";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Income> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Income(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5) ));
        }
        return arrayList;
    }

    public static boolean saveIncomeData(Income income) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO income VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,income.getPaymentId(),income.getDate(),income.getMonth(),income.getYear(),income.getAmount());
    }

    public static double calculateDailyIncome(String date, String month, String year) throws SQLException, ClassNotFoundException {
        String sql = "SELECT amount FROM income WHERE date = ? and month = ? and year = ?";
        ResultSet rst = CrudUtil.execute(sql,date,month,year);
        double total=0;
        while (rst.next()){
            total += rst.getDouble(1);
        }
        return total;
    }

    public static double calculateAnnualIncome(String year) throws SQLException, ClassNotFoundException {
        String sql = "SELECT amount FROM income WHERE year = ?";
        ResultSet rst = CrudUtil.execute(sql,year);
        double total=0;
        while (rst.next()){
            total += rst.getDouble(1);
        }
        return total;
    }

    public static double calculateMonthlyIncome(String month, String year) throws SQLException, ClassNotFoundException {
        String sql = "SELECT amount FROM income WHERE month = ? and year = ?";
        ResultSet rst = CrudUtil.execute(sql,month,year);
        double total=0;
        while (rst.next()){
            total += rst.getDouble(1);
        }
        return total;
    }
}
