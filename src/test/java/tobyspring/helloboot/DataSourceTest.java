package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;

@HellobootTest
public class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void connect() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
