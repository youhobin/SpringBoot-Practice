package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    public void helloController() throws Exception {
        //given
        HelloController helloController = new HelloController(name -> name); // 생성자에 원래 service를 넣어줘야함

        //when
        String ret = helloController.hello("Test");

        //then
        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    public void failsHelloController() throws Exception {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
