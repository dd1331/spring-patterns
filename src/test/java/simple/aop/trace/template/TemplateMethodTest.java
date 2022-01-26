package simple.aop.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import simple.aop.trace.template.code.AbstractTemplate;
import simple.aop.trace.template.code.SubClassLogic1;
import simple.aop.trace.template.code.SubClassLogic2;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethod() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("로직1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }

    @Test
    void templateMethod1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();

    }

    @Test
    void templateMethod2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("로직 1 실행");
            }
        };
        log.info("클래스 이름={}", template1.getClass());
        template1.execute();
        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("로직 2 실행");
            }
        };
        log.info("클래스2 이름={}", template2.getClass());
        template2.execute();
    }

}
