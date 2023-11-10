package lk.ijse.medicalcenter.report.report_connection;

import lk.ijse.medicalcenter.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import rex.utils.S;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class ReportConnection {
    public static void reportConnection(String location){
        InputStream inputStream = ReportConnection.class.getResourceAsStream("/lk/ijse/medicalcenter/report/"+location);
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(compileReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
