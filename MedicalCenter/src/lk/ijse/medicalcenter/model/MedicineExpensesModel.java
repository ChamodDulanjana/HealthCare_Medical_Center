package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.MedicineExpenses;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.SQLException;

public class MedicineExpensesModel {


    public static boolean saveMedicineExpenses(MedicineExpenses medicineExpenses) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO medicine_expenses VALUES(?,?,?)";
        return CrudUtil.execute(sql,medicineExpenses.getMedicinePaymentId(),medicineExpenses.getDate(),medicineExpenses.getAmount());
    }
}
