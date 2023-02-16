package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.HelloBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 데이터베이스를 이용하여 테스트코드를 작성할 때는 항상 롤백이 되어야 한다.
 * 우리가 사용한 데이터가 데이터베이스에 남아 있다면 항상 테스트코드는 성공하는 코드를 만들 수 없다.
 * 이유는 항상 같은 상황을 전재하여 테스트가 진행되어야 하는데 매번 테스트 상황이 달라지기 떄문이다.
 */
@HelloBootTest
class DataSourceConfigTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }


}
