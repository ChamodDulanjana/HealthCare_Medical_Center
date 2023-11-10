package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.db.DBConnection;
import lk.ijse.medicalcenter.to.Payment;
import lk.ijse.medicalcenter.to.TestPayment;

import java.sql.SQLException;
import java.time.LocalDate;

public class TestPaymentModel {
    public static boolean issueBill(TestPayment testPayment) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdded = PaymentModel.addNewPayment(new Payment(testPayment.getPaymentId(), LocalDate.now(), testPayment.getPatientId(), testPayment.getNurseId()));
            if (isAdded){
                boolean isUpdated = TestModel.addNewTest(testPayment.getTestArrayList());
                if (isUpdated) {
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }
}
