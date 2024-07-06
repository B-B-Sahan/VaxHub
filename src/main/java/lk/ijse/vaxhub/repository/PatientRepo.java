package lk.ijse.vaxhub.repository;

import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRepo {
    public static List<Patient> getAll() throws SQLException {
        String sql = "SELECT * FROM patient";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Patient> patientList = new ArrayList<>();
        while (resultSet.next()) {
            String patient_id = resultSet.getString(1);
            String register_id = resultSet.getString(2);
            String first_name = resultSet.getString(3);
            String last_name = resultSet.getString(4);
            String gender = resultSet.getString(5);
            String birth_day = resultSet.getString(6);
            String email = resultSet.getString(7);
            String address = resultSet.getString(8);
            String contact_number= resultSet.getString(9);
            String date_administer = resultSet.getString(10);
            String adverse_reaction = resultSet.getString(11);
            String vaccine_name= resultSet.getString(12);
            String weight_value = resultSet.getString(13);
            String hight_value = resultSet.getString(14);


           Patient  patient = new Patient(patient_id,register_id, first_name,last_name, gender,birth_day,email,address, contact_number,date_administer,adverse_reaction,vaccine_name,weight_value,hight_value);
           patientList.add(patient);
        }
        return patientList;
    }


    public static boolean save(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  patient.getPatient_id());
        pstm.setObject(2,  patient.getRegister_id());
        pstm.setObject(3,  patient.getFirst_name());
        pstm.setObject(4,  patient.getLast_name());
        pstm.setObject(5,  patient.getGender());
        pstm.setObject(6,  patient.getBirth_day());
        pstm.setObject(7,  patient.getEmail());
        pstm.setObject(8,  patient.getAddress());
        pstm.setObject(9,  patient.getContact_number());
        pstm.setObject(10,  patient.getDate_administer());
        pstm.setObject(11,  patient.getAdverse_reaction());
        pstm.setObject(12,  patient.getVaccine_name());
        pstm.setObject(13,  patient.getWeight_value());
        pstm.setObject(14,  patient.getHight_value());

        return pstm.executeUpdate() > 0;
    }


    public static boolean update(Patient patient) throws SQLException {
        String sql = "UPDATE patient SET register_id=?,first_name=?,last_name=?, gender=?, birth_day=?, email=?, address=?, contact_number=?,date_administer=?, adverse_reaction=?,vaccine_name=?, weight_value=?,hight_value=? WHERE patient_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);



        pstm.setObject(1,  patient.getRegister_id());
        pstm.setObject(2,  patient.getFirst_name());
        pstm.setObject(3,  patient.getLast_name());
        pstm.setObject(4,  patient.getGender());
        pstm.setObject(5,  patient.getBirth_day());
        pstm.setObject(6,  patient.getEmail());
        pstm.setObject(7,  patient.getAddress());
        pstm.setObject(8,  patient.getContact_number());
        pstm.setObject(9,  patient.getDate_administer());
        pstm.setObject(10,  patient.getAdverse_reaction());
        pstm.setObject(11,  patient.getVaccine_name());
        pstm.setObject(12,  patient.getWeight_value());
        pstm.setObject(13,  patient.getHight_value());
        pstm.setObject(14,  patient.getPatient_id());




        return pstm.executeUpdate() > 0;
    }


    public static boolean delete(String patient_id) throws SQLException {
        String sql = "DELETE FROM patient WHERE patient_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, patient_id);

        return pstm.executeUpdate() > 0;
    }



    public static Patient searchById(String patient_id) throws SQLException {
        String sql = "SELECT * FROM patient WHERE patient_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,patient_id);
        ResultSet resultSet = pstm.executeQuery();

        Patient patient = null;




        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String register_id = resultSet.getString(2);
            String first_name = resultSet.getString(3);
            String last_name = resultSet.getString(4);
            String gender = resultSet.getString(5);
            String birth_day = resultSet.getString(6);
            String email = resultSet.getString(7);
            String address = resultSet.getString(8);
            String contact_number= resultSet.getString(9);
            String date_administer = resultSet.getString(10);
            String adverse_reaction = resultSet.getString(11);
            String vaccine_name= resultSet.getString(12);
            String weight_value = resultSet.getString(13);
            String hight_value = resultSet.getString(14);

           patient = new Patient( id,register_id, first_name,last_name, gender,birth_day,email,address, contact_number,date_administer,adverse_reaction,vaccine_name,weight_value,hight_value);
        }
        return patient;
    }


    public static List<String> getPIds() throws SQLException {
        String sql = "SELECT patient_id FROM patient";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> pIdList = new ArrayList<>();

        while (resultSet.next()) {
            pIdList.add(resultSet.getString(1));
        }
        return pIdList;
    }
}


