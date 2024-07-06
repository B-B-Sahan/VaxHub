package lk.ijse.vaxhub.model;
import lk.ijse.vaxhub.model.Vaccination;
import lk.ijse.vaxhub.model.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceVaccination {
    private Vaccine vaccine;
    private Vaccination vaccination;
}
