package lk.ijse.vaxhub.repository;
import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.model.Appoiment;
import lk.ijse.vaxhub.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AppoimentRepo {
    public static List<Appoiment> getAll() throws SQLException {
        String sql = "SELECT * FROM appoiment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Appoiment> appoimentList = new ArrayList<>();
        while (resultSet.next()) {
            String appoiment_id = resultSet.getString(1);
            String employee_id = resultSet.getString(2);
            String patient_id = resultSet.getString(3);
            String appoiment_date = resultSet.getString(4);
            String appoiment_time = resultSet.getString(5);

            Appoiment appoiment = new Appoiment(appoiment_id,employee_id, patient_id, appoiment_date, appoiment_time);
            appoimentList.add(appoiment);
        }
        return appoimentList;

    }

    public static Appoiment searchById(String patientId) throws SQLException {
        String sql = "SELECT * FROM appoiment WHERE  patient_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,patientId);
        ResultSet resultSet = pstm.executeQuery();

      Appoiment appoiment = null;

        if (resultSet.next()) {
            String appoiment_id = resultSet.getString(1);
            String employee_id = resultSet.getString(2);
            String id = resultSet.getString(3);
            String appoiment_date = resultSet.getString(4);
            String appoiment_time = resultSet.getString(5);


           appoiment = new Appoiment(appoiment_id,employee_id,id,  appoiment_date, appoiment_time);
        }
        return appoiment;
    }





    public static boolean update(Appoiment appoiment) throws SQLException {
        String sql = "UPDATE  appoiment SET employee_id=?,patient_id=?, appoiment_date=?, appoiment_time=? WHERE appoiment_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, appoiment.getEmployee_id());
        pstm.setObject(2, appoiment.getPatient_id());
        pstm.setObject(3, appoiment.getAppoiment_date());
        pstm.setObject(4, appoiment.getAppoiment_time());
        pstm.setObject(5, appoiment.getAppoiment_id());


        return pstm.executeUpdate() > 0;
    }


    public static boolean save(Appoiment appoiment) throws SQLException {
        String sql = "INSERT INTO appoiment VALUES(?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, appoiment.getAppoiment_id());
        pstm.setObject(2, appoiment.getEmployee_id());
        pstm.setObject(3, appoiment.getPatient_id());
        pstm.setObject(4, appoiment.getAppoiment_date());
        pstm.setObject(5, appoiment.getAppoiment_time());


        return pstm.executeUpdate() > 0;
    }





    public static boolean delete(String appoiment_id) throws SQLException {
        String sql = "DELETE FROM patient WHERE appoiment_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, appoiment_id);

        return pstm.executeUpdate() > 0;
    }

    public int countAppoiment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(appoiment_id) FROM appoiment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            int idd = Integer.parseInt(resultSet.getString(1));
            return idd;
        }
        return Integer.parseInt(null);
    }
    }