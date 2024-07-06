package lk.ijse.vaxhub.repository;

import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.model.Patient;
import lk.ijse.vaxhub.model.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterRepo {
    public static List<Register> getAll() throws SQLException {
        String sql = "SELECT * FROM register";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Register> registerList = new ArrayList<>();
        while (resultSet.next()) {
            String register_id = resultSet.getString(1);
            String first_name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            String address = resultSet.getString(4);
            String contact_number= resultSet.getString(5);



            Register  register = new Register(register_id, first_name,last_name,address, contact_number);
            registerList.add(register);
        }
        return registerList;
    }


    public static boolean save(Register register) throws SQLException {
        String sql = "INSERT INTO register VALUES(?, ?, ?, ?, ? )";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  register.getRegister_id());
        pstm.setObject(2,  register.getFirst_name());
        pstm.setObject(3,  register.getLast_name());
        pstm.setObject(4,  register.getAddress());
        pstm.setObject(5,  register.getContact_number());


        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Register register) throws SQLException {
        String sql = "UPDATE register SET first_name=?,last_name=?, address=?, contact_number=? WHERE register_id=?,";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);


        pstm.setObject(1,  register.getFirst_name());
        pstm.setObject(2,  register.getLast_name());
        pstm.setObject(3,  register.getAddress());
        pstm.setObject(4,  register.getContact_number());
        pstm.setObject(5,  register.getRegister_id());




        return pstm.executeUpdate() > 0;

    }

    public static boolean delete(String register_id) throws SQLException {
        String sql = "DELETE FROM register WHERE register_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, register_id);

        return pstm.executeUpdate() > 0;
    }

    public static List<String> getRIds() throws SQLException {
        String sql = "SELECT register_id FROM register";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static Register searchById(String register_id) throws SQLException {
        String sql = "SELECT * FROM register WHERE register_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,register_id);
        ResultSet resultSet = pstm.executeQuery();

        Register register = null;




        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String first_name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            String address = resultSet.getString(4);
            String contact_number= resultSet.getString(5);

          register = new Register(register_id, first_name,last_name,address, contact_number);
        }
        return register;
    }
}
