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
        //activate the DDL generation
        this.manager.find(Cedente.class, 1L);
        
        try (Connection connection = dataSource.getConnection()) {
            // create a Statement from the connection
            Statement statement = connection.createStatement();

            // insert the data
            statement.executeUpdate("insert into conta_bancaria (codigo, agencia, digito_agencia, numero, digito_conta, codigo_carteira) values (1,1010, '0', 2020, '0', 6);");
            statement.executeUpdate("insert into cedente (codigo, nome, cnpj, codigo_conta_bancaria) values (1,'AT Systems, Solução em Desenvolvimento de Sistemas', '55.947.373/0001-66', 1);");

        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
