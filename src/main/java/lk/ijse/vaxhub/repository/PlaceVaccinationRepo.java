package lk.ijse.vaxhub.repository;


import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.model.PlaceVaccination;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceVaccinationRepo {
    public static boolean placeVaccination(PlaceVaccination po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isVaccineSaved = VaccineRepo.qtyupdate(po.getVaccine());
            if (isVaccineSaved) {
                boolean isVaccinationSaved = VaccinationRepo.save(po.getVaccination());
                if (isVaccinationSaved) {
                        connection.commit();
                        return true;
                    }
                }
            connection.rollback();

            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}


