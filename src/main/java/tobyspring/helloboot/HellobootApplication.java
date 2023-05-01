package tobyspring.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@ComponentScan
public class HellobootApplication {
	@Bean
	public ServletWebServerFactory serverFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
		// 스프링 컨테이너 만들기
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() { // 애노테이션으로 변경
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//				dispatcherServlet.setApplicationContext(this); // 스프링 컨테이너를 지정해줌

				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", dispatcherServlet)
						.addMapping("/*"); //서블릿을 등록하고 매핑을 추가, 프론트컨트롤러에서 모든 매핑추가
				}); // 파라미터로 서블릿 등록 -> 익명클래스를 람다식으로.

				webServer.start(); // 톰캣 서블릿 컨테이너가 동작
			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh(); // 빈 만들어줌.


	}

}
