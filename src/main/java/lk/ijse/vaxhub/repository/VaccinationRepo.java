package lk.ijse.vaxhub.repository;

import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.model.Patient;
import lk.ijse.vaxhub.model.Vaccination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccinationRepo {
    public static List<Vaccination> getAll() throws SQLException {
        String sql = "SELECT * FROM vaccination";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Vaccination> vaccinationList = new ArrayList<>();
        while (resultSet.next()) {
            String patient_id = resultSet.getString(1);
            String vaccine_id = resultSet.getString(2);
            String vaccine_name = resultSet.getString(3);
            String date = resultSet.getString(4);


           Vaccination  vaccination = new Vaccination(patient_id, vaccine_id,vaccine_name, date);
            vaccinationList.add(vaccination);
        }
        return vaccinationList;

    }

    public static boolean delete(String patient_id) throws SQLException {
        String sql = "DELETE FROM vaccination WHERE patient_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, patient_id);

        return pstm.executeUpdate() > 0;


    }

    public static boolean save(Vaccination vaccination) throws SQLException {
        String sql = "INSERT INTO vaccination VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  vaccination.getPatient_id());
        pstm.setObject(2,  vaccination.getVaccine_id());
        pstm.setObject(3,  vaccination.getVaccine_name());
        pstm.setObject(4,  vaccination.getDate());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Vaccination vaccination) throws SQLException {
        String sql = "UPDATE vaccination SET vaccine_id=?,vaccine_name=?, date=? WHERE patient_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  vaccination.getVaccine_id());
        pstm.setObject(2,  vaccination.getVaccine_name());
        pstm.setObject(3,  vaccination.getDate());
        pstm.setObject(4,  vaccination.getPatient_id());



        return pstm.executeUpdate() > 0;

    }

    public static Vaccination searchById(String patient_id) throws SQLException {

        String sql = "SELECT * FROM vaccination WHERE patient_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,patient_id);
        ResultSet resultSet = pstm.executeQuery();

        Vaccination vaccination = null;




        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String vaccine_id = resultSet.getString(2);
            String vaccine_name = resultSet.getString(3);
            String date = resultSet.getString(4);

           vaccination = new Vaccination( id, vaccine_id,vaccine_name, date);
        }
        return vaccination;
    }

}

