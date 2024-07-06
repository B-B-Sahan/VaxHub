package lk.ijse.vaxhub.model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VaccinationTm {
    private String patient_id;
    private String vaccine_id;
    private String vaccine_name;
    private String date;




}
