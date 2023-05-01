package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//SpringApplication 의 모습
public class MySpringApplication {
    public static void run(Class<?> applicationClass, String[]... args) {
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
        applicationContext.register(applicationClass);
        applicationContext.refresh(); // 빈 만들어줌.
    }
}
