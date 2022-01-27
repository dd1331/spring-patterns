package simple.aop.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import simple.aop.trace.strategy.code.template.Callback;
import simple.aop.trace.strategy.code.template.TimeLogTemplate;

@Slf4j
public class TemplateCallbackTest {

    @Test
    void callback() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("logic 1 executed");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("logic 2 executed");
            }
        });
    }
    @Test
    void callback2() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("logic 1 executed"));

        template.execute(() -> log.info("logic 2 executed"));
    }
}
