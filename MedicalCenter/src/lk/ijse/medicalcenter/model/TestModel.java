package lk.ijse.medicalcenter.model;

import lk.ijse.medicalcenter.to.MedicineStore;
import lk.ijse.medicalcenter.to.PaymentDetail;
import lk.ijse.medicalcenter.to.Test;
import lk.ijse.medicalcenter.to.TestPayment;
import lk.ijse.medicalcenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestModel {

    public static String generateNextTestId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT testId FROM test ORDER BY testId DESC LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);

        if (rst.next()){
            return generateNextTestId(rst.getString(1));
        }
        return generateNextTestId(rst.getString(null));
    }

    public static String generateNextTestId(String currentTestId){
        if (currentTestId != null){
            String[] split = currentTestId.split("T");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return String.format("T%03d", id);
        }
        return "T001";
    }

    public static boolean addNewTest(ArrayList<Test> testArrayList ) throws SQLException, ClassNotFoundException{
        for (Test test : testArrayList) {
            if (!addNewTest(new Test(test.getTestId(),test.getDescription(),test.getTotalFee(),test.getPaymentId()))){
                return false;
            }
        }
        return true;
    }

    public static boolean addNewTest(Test test) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO test VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,test.getTestId(),test.getDescription(),test.getTotalFee(),test.getPaymentId());
    }
}
