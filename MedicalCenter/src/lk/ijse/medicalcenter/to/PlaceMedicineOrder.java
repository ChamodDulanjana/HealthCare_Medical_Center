package lk.ijse.medicalcenter.to;

import java.util.ArrayList;

public class PlaceMedicineOrder {
    private String medicinePaymentId;
    private String supplierId;
    private ArrayList<StoreDetail> storeDetailsList = new ArrayList<>();

    public PlaceMedicineOrder() {
    }

    public PlaceMedicineOrder(String medicinePaymentId, String supplierId, ArrayList<StoreDetail> storeDetailsList) {
        this.medicinePaymentId = medicinePaymentId;
        this.supplierId = supplierId;
        this.storeDetailsList = storeDetailsList;
    }

    public String getMedicinePaymentId() {
        return medicinePaymentId;
    }

    public void setMedicinePaymentId(String medicinePaymentId) {
        this.medicinePaymentId = medicinePaymentId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public ArrayList<StoreDetail> getStoreDetailsList() {
        return storeDetailsList;
    }

    public void setStoreDetailsList(ArrayList<StoreDetail> storeDetailsList) {
        this.storeDetailsList = storeDetailsList;
    }
}
