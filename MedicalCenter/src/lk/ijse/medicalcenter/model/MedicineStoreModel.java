package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.to.PaymentDetail;
import lk.ijse.medicalcenter.to.StoreDetail;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineStoreModel {
    public static ArrayList<MedicineStore> viewMedicineModel() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM medicine_store";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<MedicineStore> medicineStoreArrayList = new ArrayList<>();
        while (rst.next()){
            MedicineStore medicineStore = new MedicineStore(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5), rst.getInt(6));
            medicineStoreArrayList.add(medicineStore);
        }

        return medicineStoreArrayList;
    }


    public static boolean addNewMedicine(MedicineStore medicineStore) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO medicine_store VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql,medicineStore.getCode(),medicineStore.getDescription(),medicineStore.getCompany(),medicineStore.getWeight(),medicineStore.getUnitPrice(),medicineStore.getQtyOnHand());
    }

    public static MedicineStore SearchMedicine(String  code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM medicine_store WHERE code = '"+code+"'";
        ResultSet rst = CrudUtil.execute(sql);

        if (rst.next()){
            MedicineStore medicineStore = new MedicineStore(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5), rst.getInt(6));
            return medicineStore;
        }
        return null;
    }

    public static boolean updateMedicine(MedicineStore medicineStore) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE medicine_store SET description = ?, company = ?, mg = ?,unitPrice = ?, qtyOnHand = ? WHERE code = ?";
        //System.out.println(medicineStore.getQtyOnHand());
        return CrudUtil.execute(sql,medicineStore.getDescription(),medicineStore.getCompany(),medicineStore.getWeight(),medicineStore.getUnitPrice(),medicineStore.getQtyOnHand(),medicineStore.getCode());
    }

    public static boolean DeleteMedicine(String code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM medicine_store WHERE code = ?";
        return CrudUtil.execute(sql,code);
    }

    public static boolean updateStore(ArrayList<StoreDetail> storeDetailsList) throws SQLException, ClassNotFoundException {
        for (StoreDetail storeDetail : storeDetailsList) {
            if (!updateStore(new MedicineStore(storeDetail.getCode(),storeDetail.getDescription(),storeDetail.getCompany(),storeDetail.getWeight(),storeDetail.getUnitPrice(),storeDetail.getQty()))){
                return false;
            }
        }
        return true;
    }

    public static boolean updateStore(MedicineStore medicineStore) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE medicine_store SET unitPrice = ?, qtyOnHand = qtyOnHand + ? WHERE code = ?";
        return CrudUtil.execute(sql, medicineStore.getUnitPrice(),medicineStore.getQtyOnHand(),medicineStore.getCode());
    }

    public static boolean updateMedicineStore(ArrayList<PaymentDetail> paymentDetailsList) throws SQLException, ClassNotFoundException {
        for (PaymentDetail paymentDetail : paymentDetailsList) {
            if (!updateMedicineStore(new MedicineStore(paymentDetail.getCode(),paymentDetail.getDescription(),paymentDetail.getWeight(),paymentDetail.getUnitPrice(),paymentDetail.getQty()))){
                return false;
            }
        }
        return true;
    }

    public static boolean updateMedicineStore(MedicineStore medicineStore) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE medicine_store SET  qtyOnHand = qtyOnHand - ? WHERE code = ?";
        return CrudUtil.execute(sql, medicineStore.getQtyOnHand(),medicineStore.getCode());
    }

    public static String generateNextCode() throws SQLException, ClassNotFoundException {
        String sql = "SELECT code FROM medicine_store ORDER BY code DESC LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);

        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public static ArrayList<String> getMedicineNames() throws SQLException, ClassNotFoundException {
        String sql = "SELECT description  FROM medicine_store";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<String> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(rst.getString(1));
        }
        return arrayList;
    }

    public static MedicineStore SearchMedicineUsingName(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM medicine_store WHERE description = ?";
        ResultSet rst = CrudUtil.execute(sql, name);

        if (rst.next()){
            MedicineStore medicineStore = new MedicineStore(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5), rst.getInt(6));
            return medicineStore;
        }
        return null;
    }
}
