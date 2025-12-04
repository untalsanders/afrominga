package com.untalsanders.afrominga.application.service;

import com.untalsanders.afrominga.domain.model.Afro;
import com.untalsanders.afrominga.domain.usecase.RetrieveAfroUseCase;
import com.untalsanders.afrominga.infrastructure.persistence.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AfrosService implements RetrieveAfroUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfrosService.class);

    public List<Afro> getAllAfros() {
        List<Afro> afroList = new ArrayList<>();

        try (
            var connection = Database.getConnection();
            var stmt = connection.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM afros");
        ) {
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String fullName = rs.getString("nombresapellidos");
                afroList.add(new Afro(id, fullName));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return afroList;
    }
}
