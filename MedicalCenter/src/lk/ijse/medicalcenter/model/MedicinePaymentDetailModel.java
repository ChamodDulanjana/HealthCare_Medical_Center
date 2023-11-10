package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.to.StoreDetail;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class MedicinePaymentDetailModel {
    public static boolean saveMedicineDetails(ArrayList<StoreDetail> storeDetailsList) throws SQLException, ClassNotFoundException {
        for ( StoreDetail storeDetail : storeDetailsList) {
            if (!save(storeDetail)){
                return false;
            }
        }
        return true;
    }

    private static boolean save (StoreDetail storeDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO medicinePayment_detail VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,storeDetail.getMedicinePaymentId(),storeDetail.getCode(),storeDetail.getQty(),storeDetail.getUnitPrice());
    }
}
