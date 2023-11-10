package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.PaymentDetail;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDetailModel {
    public static boolean savePaymentDetail(ArrayList<PaymentDetail> paymentDetailsList) throws SQLException, ClassNotFoundException {
        for ( PaymentDetail paymentDetail : paymentDetailsList) {
            if (!save(paymentDetail)){
                return false;
            }
        }
        return true;
    }

    private static boolean save(PaymentDetail paymentDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment_detail VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,paymentDetail.getPaymentId(),paymentDetail.getCode(),paymentDetail.getQty(),paymentDetail.getUnitPrice());

    }

//    public static double calculateTotal() throws SQLException, ClassNotFoundException {
//        String sql = "SELECT qty*unitPrice FROM payment_detail ";
//        ResultSet rst = CrudUtil.execute(sql);
//        double total =0;
//        while (rst.next()){
//            total += rst.getDouble(1);
//        }
//        return total;
//    }
}
