package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.Supplier;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {

    public static boolean addNewSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier VALUES (?,?,?,?,?,?) ";
        return CrudUtil.execute(sql,supplier.getSupId(),supplier.getName(),supplier.getCompany(),supplier.getAddress(),supplier.getGender(),supplier.getContact());
    }

    public static ResultSet getLastId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT supId FROM supplier ORDER BY supId DESC LIMIT 1";
        return CrudUtil.execute(sql);
    }

    public static Supplier searchSupplier(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier WHERE supId = ?";
        ResultSet rst = CrudUtil.execute(sql,id);

        if (rst.next()){
            Supplier supplier = new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getInt(6));
            return supplier;
        }
        return null;
    }

    public static boolean updateSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE supplier SET name = ?, company = ?, address = ?, gender = ?, contact = ?, WHERE supId = ?";
        return CrudUtil.execute(sql,supplier.getName(),supplier.getCompany(),supplier.getAddress(),supplier.getGender(),supplier.getContact(),supplier.getSupId());
    }

    public static boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supplier WHERE supId = ?";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<Supplier> viewSupplier() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<Supplier> supplierArrayList = new ArrayList<>();
        while (rst.next()){
            Supplier supplier = new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getInt(6));
            supplierArrayList.add(supplier);
        }
        return supplierArrayList;
    }

    public static ArrayList<String> getSupplierNames() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name  FROM supplier";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();
        while (rst.next()){
            idList.add(rst.getString(1));
        }
        return idList;
    }

    public static String getSupplierId(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT supId FROM supplier WHERE name = ?";
        ResultSet rst = CrudUtil.execute(sql, name);

        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public static String getSupplierCompany(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT company FROM supplier WHERE name = ?";
        ResultSet rst = CrudUtil.execute(sql, name);

        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
