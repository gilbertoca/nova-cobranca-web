package com.andreitoledo.cobranca.util.jpa;

import com.andreitoledo.cobranca.model.Cedente;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@ApplicationScoped
@Startup
public class DatabaseInitializer {

    @Resource(name = "Default JDBC Database")
    private DataSource dataSource;
    
    @PersistenceContext(unitName = "boletoCobrancaPU")
    private EntityManager manager;

    @PostConstruct
    public void init() {
        
        this.manager.find(Cedente.class, 1L);
        
        try (Connection connection = dataSource.getConnection()) {
            // create a Statement from the connection
            Statement statement = connection.createStatement();

            // insert the data
            statement.executeUpdate("INSERT INTO conta_bancaria " + "VALUES (1010, '0', 2020, '0', 6)");
            statement.executeUpdate("INSERT INTO cedente " + "VALUES ('AT Systems, Solução em Desenvolvimento de Sistemas', '55.947.373/0001-66', 1)");

        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
