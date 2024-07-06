package lk.ijse.vaxhub.repository;

import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.model.EmployeeAttendance;
import lk.ijse.vaxhub.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceRepo {
    public static List<EmployeeAttendance> getAll() throws SQLException {
        String sql = "SELECT * FROM attendance";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<EmployeeAttendance> employeeAttendanceList = new ArrayList<>();
        while (resultSet.next()) {
            String attendance_id = resultSet.getString(1);
            String employee_id  = resultSet.getString(2);
            String date = resultSet.getString(3);
            String in_time = resultSet.getString(4);
            String out_time = resultSet.getString(5);
            String attendance = resultSet.getString(6);


           EmployeeAttendance  employeeAttendance = new EmployeeAttendance(attendance_id, employee_id ,date,in_time, out_time,attendance);
            employeeAttendanceList.add(employeeAttendance);
        }
        return employeeAttendanceList;
    }


    public static boolean delete(String attendanceId) throws SQLException {
        String sql = "DELETE FROM attendance WHERE attendance_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, attendanceId);

        return pstm.executeUpdate() > 0;
    }




    public static boolean save(EmployeeAttendance employeeAttendance) throws SQLException {
        String sql = "INSERT INTO attendance VALUES(?, ?, ?, ?, ?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  employeeAttendance.getAttendance_id());
        pstm.setObject(2,  employeeAttendance.getEmployee_id());
        pstm.setObject(3,  employeeAttendance.getDate());
        pstm.setObject(4,  employeeAttendance.getIn_time());
        pstm.setObject(5, employeeAttendance.getOut_time());
        pstm.setObject(6, employeeAttendance.getAttendance());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(EmployeeAttendance employeeAttendance) throws SQLException {
        String sql = "UPDATE attendance SET employee_id=?, date=?,in_time=?, out_time=?,attendance=? WHERE attendance_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  employeeAttendance.getEmployee_id());
        pstm.setObject(2,  employeeAttendance.getDate());
        pstm.setObject(3,  employeeAttendance.getIn_time());
        pstm.setObject(4,  employeeAttendance.getOut_time());
        pstm.setObject(5, employeeAttendance.getAttendance());
        pstm.setObject(6, employeeAttendance.getAttendance_id());




        return pstm.executeUpdate() > 0;

    }

    public static EmployeeAttendance searchById(String employeeId) throws SQLException {
        String sql = "SELECT * FROM attendance WHERE employee_id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,employeeId);
        ResultSet resultSet = pstm.executeQuery();

        EmployeeAttendance employeeAttendance = null;




        if (resultSet.next()) {
            String attendance_id = resultSet.getString(1);
            String id = resultSet.getString(2);
            String  date = resultSet.getString(3);
            String in_time = resultSet.getString(4);
            String out_time = resultSet.getString(5);
            String attendance = resultSet.getString(6);

           employeeAttendance = new EmployeeAttendance(attendance_id,id,date,in_time,out_time,attendance);
        }
        return employeeAttendance;
    }

    public int countEmpAttendance() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(employee_id) FROM attendance";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            int idd = Integer.parseInt(resultSet.getString(1));
            return idd;
        }
        return Integer.parseInt(null);
    }



    }


