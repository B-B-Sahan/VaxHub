package lk.ijse.vaxhub.repository;

import lk.ijse.vaxhub.db.DbConnection;

import lk.ijse.vaxhub.model.Employee;
import lk.ijse.vaxhub.model.Vaccine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccineRepo {
    public static Vaccine searchById(String vaccine_name) throws SQLException {
        String sql = "SELECT * FROM vaccine WHERE vaccine_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,vaccine_name);
        ResultSet resultSet = pstm.executeQuery();

        Vaccine vaccine = null;




        if (resultSet.next()) {
            String vaccine_id = resultSet.getString(1);
            String employee_id = resultSet.getString(2);
            String name = resultSet.getString(3);
            String vaccine_date = resultSet.getString(4);
            String manufacture = resultSet.getString(5);
            String quantity = resultSet.getString(6);


                vaccine = new Vaccine(vaccine_id,employee_id,name,vaccine_date,manufacture,quantity);
        }
        return vaccine;
    }



    public static boolean update(Vaccine vaccine) throws SQLException {
        String sql = "UPDATE vaccine SET employee_id=?,  vaccine_name=?, vaccine_date=?, manufacture=?, quantity=? WHERE vaccine_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  vaccine.getEmployee_id());
        pstm.setObject(2,  vaccine.getVaccine_name());
        pstm.setObject(3,  vaccine.getVaccine_date());
        pstm.setObject(4,  vaccine.getManufacture());
        pstm.setObject(5,  vaccine.getQuantity());
        pstm.setObject(6,  vaccine.getVaccine_id());



        return pstm.executeUpdate() > 0;


    }

    public static boolean save(Vaccine vaccine) throws SQLException {
        String sql = "INSERT INTO vaccine VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  vaccine.getVaccine_id());
        pstm.setObject(2,  vaccine.getEmployee_id());
        pstm.setObject(3,  vaccine.getVaccine_name());
        pstm.setObject(4,  vaccine.getVaccine_date());
        pstm.setObject(5,  vaccine.getManufacture());
        pstm.setObject(6,  vaccine.getQuantity());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String vaccine_id) throws SQLException {
        String sql = "DELETE FROM vaccine WHERE vaccine_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, vaccine_id);

        return pstm.executeUpdate() > 0;

    }

    public static List<Vaccine> getAll() throws SQLException {
        String sql = "SELECT * FROM vaccine";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Vaccine> vaccineList = new ArrayList<>();
        while (resultSet.next()) {
            String vaccine_id = resultSet.getString(1);
            String employee_id = resultSet.getString(2);
            String vaccine_name = resultSet.getString(3);
            String vaccine_date = resultSet.getString(4);
            String manufacture = resultSet.getString(5);
            String quantity = resultSet.getString(6);

            Vaccine vaccine = new Vaccine(vaccine_id,employee_id,vaccine_name,vaccine_date,manufacture,quantity);
            vaccineList.add(vaccine);
        }
        return vaccineList;

    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT vaccine_id FROM vaccine";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static boolean qtyupdate(Vaccine vaccine) throws SQLException {
        System.out.println("pk "+vaccine.getQuantity());
        String sql = "UPDATE vaccine SET quantity = quantity - ? WHERE vaccine_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);


        pstm.setString(1, "1");
        pstm.setString(2, vaccine.getVaccine_id());

        return pstm.executeUpdate() > 0;
    }
    }


