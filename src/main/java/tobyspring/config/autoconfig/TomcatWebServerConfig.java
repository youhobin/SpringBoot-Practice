package tobyspring.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
//    @Value("${contextPath:}")    // 같은 이름의 프로퍼티로 치환해줌. 이를 교체하려면 PropertySourcesPlaceholderConfigurer의 빈 필요
//    String contextPath;
//
//    @Value("${port:8080}")  // : 이후 기본값
//    int port;

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean   // 존재하지 않으면 빈을 등록하도록. 유저 구성 정보 빈은 이미 등록이 되므로.
    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        factory.setContextPath(properties.getContextPath()); // /app가 URI에 붙게됨
        factory.setPort(properties.getPort());

        return factory;
    }


}
