package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.Payment;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {

    public static String  generateNextPayId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT payId FROM payment ORDER BY payId DESC LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);

        if (rst.next()){
            return generateNextPayId(rst.getString(1));
        }
        return generateNextPayId(rst.getString(null));
    }

    public static String generateNextPayId(String currentPayId){
        if (currentPayId != null){
            String[] split = currentPayId.split("Pay");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return String.format("Pay%03d", id);
        }
        return "Pay001";
    }


    public static boolean addNewPayment(Payment payment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql, payment.getPaymentId(), payment.getDate(), payment.getPatientId(), payment.getNurseId());

    }
}
