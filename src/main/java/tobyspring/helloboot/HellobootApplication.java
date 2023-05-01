package tobyspring.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너 만들기
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);// 어떤 메타정보로 만들지
		applicationContext.refresh(); // 빈 만들어줌.

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) { // hello URL일때
						String name = req.getParameter("name");

						// 빈을 가져와서 사용
						HelloController helloController = applicationContext.getBean(HelloController.class);
						String ret = helloController.hello(name);

						//응답 : 상태코드, 헤더 (바디가 어떤 타입인지), 바디 3가지가 필요
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE); //String 값은 오타발생 확률이 있음.
						resp.getWriter().println(ret);

					} else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}

				}
			}).addMapping("/*"); //서블릿을 등록하고 매핑을 추가, 프론트컨트롤러에서 모든 매핑추가

		}); // 파라미터로 서블릿 등록 -> 익명클래스를 람다식으로.

		webServer.start(); // 톰캣 서블릿 컨테이너가 동작
	}

}
