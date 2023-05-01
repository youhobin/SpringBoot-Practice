package tobyspring.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    // 스프링 컨테이너에서 주입받도록
    private final HelloService helloService;
//    private ApplicationContext applicationContext; // final 아님. final 은 늦어도 생성자가 다 완료되는 시점에 초기화가 되어야함

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
//        this.applicationContext = applicationContext;
    }

    @GetMapping("/hello")
    public String hello(String name) {
//        SimpleHelloService helloService = new SimpleHelloService();
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException();

        return helloService.sayHello(name);
    }
}
