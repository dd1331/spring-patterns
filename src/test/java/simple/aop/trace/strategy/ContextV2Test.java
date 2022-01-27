package simple.aop.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import simple.aop.trace.strategy.code.strategy.*;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {

        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {

        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("logic 1 실행");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("logic 2 실행");
            }
        });
    }

    @Test
    void strategyV() {

        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("logic 1 실행"));
        context.execute(() -> log.info("logic 2 실행"));
    }
}



