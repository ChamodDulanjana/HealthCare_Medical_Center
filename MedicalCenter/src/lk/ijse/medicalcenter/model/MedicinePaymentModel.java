package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.db.DBConnection;
import lk.ijse.medicalcenter.to.MedicinePayment;
import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.to.PaymentDetail;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicinePaymentModel {

    public static String  generateNextMedicinePaymentId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT medicinePayId  FROM medicinePayment ORDER BY medicinePayId  DESC LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);

        if (rst.next()){
            return generateNextMedicinePaymentId(rst.getString(1));
        }
        return generateNextMedicinePaymentId(rst.getString(null));
    }

    private static String generateNextMedicinePaymentId(String currentMedicineOrderId){
        if (currentMedicineOrderId != null) {
            String[] split = currentMedicineOrderId.split("MPI");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return String.format("MPI%03d", id);
        }
        return "MPI001";
    }


    public static boolean addMedicinePayment(MedicinePayment medicinePayment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO medicinePayment VALUES(?, ?, ?)";
        return CrudUtil.execute(sql, medicinePayment.getMedicinePaymentId(), medicinePayment.getDate(), medicinePayment.getSupplierId());
    }

}















