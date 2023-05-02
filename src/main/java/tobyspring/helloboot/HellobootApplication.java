package tobyspring.helloboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication // 만든 SpringBootAnnotation
public class HellobootApplication {

//	@Bean
//	ApplicationRunner applicationRunner(Environment env) {
//		// 프로퍼티스에서 가져옴. 보다 환경변수가 더 우선순위 높음
//		// 보다 시스템 변수가 더 높음
//		return args -> {
//			String name = env.getProperty("my.name");
//			System.out.println("my.name: " + name);
//		};
//	}

	public static void main(String[] args) {
		// 스프링 컨테이너 만들기
		SpringApplication.run(HellobootApplication.class, args);
	}

}
