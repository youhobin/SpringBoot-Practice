package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired HelloRepository helloRepository;
    @Autowired JdbcTemplate jdbcTemplate;

    @Test
    public void findHelloFailed() throws Exception {
        Assertions.assertThat(helloRepository.findHello("hi")).isNull();

    }

    @Test
    void increaseCount() {
        helloRepository.increaseCount("hello");
        Assertions.assertThat(helloRepository.countOf("hello")).isEqualTo(1);
    }
}
