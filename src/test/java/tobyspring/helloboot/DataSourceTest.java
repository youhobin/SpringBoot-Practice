package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.Connection;

@JdbcTest
public class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void connect() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
