package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.TobySpringApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
@SpringBootTest
@ContextConfiguration(classes = TobySpringApplication.class)
class DataSourceConfigTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
