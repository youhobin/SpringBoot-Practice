package tobyspring.helloboot;

import java.util.Objects;

public class HelloController {
    // 스프링 컨테이너에서 주입받도록
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
//        SimpleHelloService helloService = new SimpleHelloService();

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
