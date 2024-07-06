package lk.ijse.vaxhub.repository;

import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.model.Employee;
import lk.ijse.vaxhub.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static Object employee_id;

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String employee_id = resultSet.getString(1);
            String first_name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            String role = resultSet.getString(4);
            String email = resultSet.getString(5);
            String address = resultSet.getString(6);
            String contact_number = resultSet.getString(7);


            Employee employee = new Employee(employee_id,first_name,last_name,role,email,address,contact_number);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public static boolean delete(String employee_id) throws SQLException {
        String sql = "DELETE FROM employee WHERE employee_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee_id);

        return pstm.executeUpdate() > 0;

    }

    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  employee.getEmployee_id());
        pstm.setObject(2,  employee.getFirst_name());
        pstm.setObject(3,  employee.getLast_name());
        pstm.setObject(4,  employee.getRole());
        pstm.setObject(5,  employee.getEmail());
        pstm.setObject(6,  employee.getAddress());
        pstm.setObject(7,  employee.getContact_number());


        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET  first_name=?,last_name=?, role=?, email=?, address=?, contact_number=? WHERE employee_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  employee.getFirst_name());
        pstm.setObject(2,  employee.getLast_name());
        pstm.setObject(3,  employee.getRole());
        pstm.setObject(4,  employee.getEmail());
        pstm.setObject(5,  employee.getAddress());
        pstm.setObject(6,  employee.getContact_number());
        pstm.setObject(7,  employee.getEmployee_id());



        return pstm.executeUpdate() > 0;

    }

    public static Employee searchById(String employee_id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employee_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,employee_id);
        ResultSet resultSet = pstm.executeQuery();

        Employee employee = null;




        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String first_name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            String role = resultSet.getString(4);
            String email = resultSet.getString(5);
            String address = resultSet.getString(6);
            String contact_number = resultSet.getString(7);

            employee = new Employee(id,first_name,last_name,role,email,address,contact_number);
        }
        return employee;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT employee_id FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    }


