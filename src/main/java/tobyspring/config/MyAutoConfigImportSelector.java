package tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) { // 임포트할 클래스를 패키지이름까지 넣어서 스트링으로 리턴
        return new String[] {
            "tobyspring.config.autoconfig.DispatcherServletConfig",
            "tobyspring.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
