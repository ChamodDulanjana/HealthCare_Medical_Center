package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.db.DBConnection;
import lk.ijse.medicalcenter.to.Payment;
import lk.ijse.medicalcenter.to.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {

    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdded = PaymentModel.addNewPayment(new Payment(placeOrder.getPaymentId(), LocalDate.now(), placeOrder.getPatientId(), placeOrder.getNurseId()));
            if (isAdded) {
                boolean isUpdate = MedicineStoreModel.updateMedicineStore(placeOrder.getPaymentDetailsList());
                if (isUpdate) {
                    boolean isOrderDetailAdded = PaymentDetailModel.savePaymentDetail(placeOrder.getPaymentDetailsList());
                    if (isOrderDetailAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
