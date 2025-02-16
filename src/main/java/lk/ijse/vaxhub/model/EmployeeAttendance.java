package lk.ijse.vaxhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeAttendance {
    private String attendance_id;
    private String employee_id;
    private String date;
    private String in_time;
    private String out_time;
    private String attendance;

}