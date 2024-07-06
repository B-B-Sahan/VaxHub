package lk.ijse.vaxhub.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AppoimentTm {
    private String appoiment_id;
    private String employee_id;
    private String patient_id;
    private String appoiment_date;
    private String appoiment_time;


}
