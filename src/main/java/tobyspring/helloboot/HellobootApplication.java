package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication // 만든 SpringBootAnnotation
public class HellobootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너 만들기
		SpringApplication.run(HellobootApplication.class, args);
	}

}
