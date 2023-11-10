package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.db.DBConnection;
import lk.ijse.medicalcenter.to.MedicinePayment;
import lk.ijse.medicalcenter.to.PlaceMedicineOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceMedicineOrderModel {
    public static boolean placeOrder(PlaceMedicineOrder placeMedicineOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdded = MedicinePaymentModel.addMedicinePayment(new MedicinePayment(placeMedicineOrder.getMedicinePaymentId(), LocalDate.now(), placeMedicineOrder.getSupplierId()));
            if (isAdded){
                boolean isUpdate = MedicineStoreModel.updateStore(placeMedicineOrder.getStoreDetailsList());
                if (isUpdate){
                    boolean isOrderDetailAdded = MedicinePaymentDetailModel.saveMedicineDetails(placeMedicineOrder.getStoreDetailsList());
                    if (isOrderDetailAdded){
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }
}
