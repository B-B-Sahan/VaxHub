package lk.ijse.vaxhub.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.vaxhub.repository.AppoimentRepo;
import lk.ijse.vaxhub.repository.EmployeeAttendanceRepo;
import javafx.scene.chart.BarChart;
import lk.ijse.vaxhub.repository.PaymentRepo;

import java.sql.SQLException;

public class DashboardFormController {
    @FXML
    private BarChart<?, ?> paymentBarchart;

    @FXML
    private Label appoimentcountLable;

    @FXML
    private Label employeecountLable;

    @FXML
    private NumberAxis paymentValueColumn;

    @FXML
    private AreaChart<?, ?> paymentchart;

    @FXML
    private Label useridLable;

    @FXML
    private Label usernameLable;


    public void initialize() throws SQLException {

        getEmpolyeeAttendance();
        getAppoimentCount();
       // setDataToBarChart();
    }

  /*  private void setDataToBarChart() {
        ObservableList<XYChart.Series<String, Integer>> barChartData = PaymentRepo.getDataToBarChart();

        paymentchart.setData(barChartData);

    }*/

    private void getAppoimentCount() {

        AppoimentRepo appoimentRepo = new AppoimentRepo();

        try{
            int count = appoimentRepo.countAppoiment();
            appoimentcountLable.setText(String.valueOf("0"+count));

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }
    private void getEmpolyeeAttendance() {

        EmployeeAttendanceRepo employeeAttendanceRepo = new EmployeeAttendanceRepo();

        try{
            int count = employeeAttendanceRepo.countEmpAttendance();
            employeecountLable.setText(String.valueOf("0"+count));

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }


    }


}
