package lk.ijse.vaxhub.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.model.Payment;
import lk.ijse.vaxhub.model.PlaceAppoiment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            String payment_id = resultSet.getString(1);
            String appoiment_id = resultSet.getString(2);
            String patient_id = resultSet.getString(3);
            String amount = resultSet.getString(4);
            String payment_date = resultSet.getString(5);


            Payment payment = new Payment(payment_id, appoiment_id, patient_id, amount, payment_date);
            paymentList.add(payment);
        }
        return paymentList;


    }

    public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, payment.getPayment_id());
        pstm.setObject(2, payment.getAppoiment_id());
        pstm.setObject(3, payment.getPatient_id());
        pstm.setObject(4, payment.getAmount());
        pstm.setObject(5, payment.getPayment_date());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Payment payment) throws SQLException {
        String sql = "UPDATE payment SET appoiment_id=?,patient_id=?,amount=?, payment_date=?WHERE payment_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, payment.getAppoiment_id());
        pstm.setObject(2, payment.getPatient_id());
        pstm.setObject(3, payment.getAmount());
        pstm.setObject(4, payment.getPayment_date());
        pstm.setObject(5, payment.getPayment_id());


        return pstm.executeUpdate() > 0;


    }

    public static Payment searchById(String patient_id) throws SQLException {
        String sql = "SELECT * FROM payment WHERE patient_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, patient_id);
        ResultSet resultSet = pstm.executeQuery();

        Payment payment = null;


        if (resultSet.next()) {
            String payment_id = resultSet.getString(1);
            String appoiment_id = resultSet.getString(2);
            String id = resultSet.getString(3);
            String amount = resultSet.getString(4);
            String payment_date = resultSet.getString(5);


            payment = new Payment(payment_id, appoiment_id, id, amount, payment_date);
        }
        return payment;
    }


    public static boolean delete(String patient_id) throws SQLException {
        String sql = "DELETE FROM payment WHERE patient_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, patient_id);

        return pstm.executeUpdate() > 0;

    }

    public static List<String> getPIds() throws SQLException {
        String sql = "SELECT payment_id FROM payment";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }


}

 /*   public static ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT paytion_id,amount FROM payment ";

        ObservableList<XYChart.Series<String, Integer>> datalist = FXCollections.observableArrayList();

        PreparedStatement pstm= connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        // Creating a new series object
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        while(resultSet.next()){
            String patient_id = resultSet.getString("paytion_id");
            String amount = resultSet.getString("amount");
            series.getData().add(new XYChart.Data<>(amount, patient_id));
    }
}*/

