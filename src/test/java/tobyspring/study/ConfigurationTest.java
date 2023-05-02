package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    public void configuration() throws Exception {
        Myconfig myconfig = new Myconfig();
        Bean1 bean1 = myconfig.bean1();
        Bean2 bean2 = myconfig.bean2();

        Assertions.assertThat(bean1.common).isNotSameAs(bean2.common); // 다름
    }

    @Test
    public void springConfiguration() throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(Myconfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends Myconfig {
        private Common common;

        @Override
        Common common() {
            if (this.common == null) this.common = super.common();

            return this.common;
        }
    }


    @Configuration // Proxy 오브젝트를 앞에 하나 두고 등록이됨.
    static class Myconfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }

    }


    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {
    }


    // Bean1  Common 에 의존한다
    // Bean2  Common 에 의존한다 .
}
