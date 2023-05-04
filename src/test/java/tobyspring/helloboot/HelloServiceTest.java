package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    public void simpleHelloService() throws Exception {
        SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);

        String ret = helloService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    private static HelloRepository helloRepositoryStub= new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };

    @Test
    public void helloDecorator() throws Exception {
        //given
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        //when
        String ret = helloDecorator.sayHello("Test");

        //then
        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
