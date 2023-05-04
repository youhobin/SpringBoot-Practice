package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tobyspring.helloboot.HelloRepository;
import tobyspring.helloboot.HelloService;
import tobyspring.helloboot.HellobootTest;

@HellobootTest
public class HelloServiceCountTest {
    @Autowired
    HelloService helloService;
    @Autowired
    HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseCount() {
        helloService.sayHello("Hi");
        Assertions.assertThat(helloRepository.countOf("Hi")).isEqualTo(1);
    }
}
