package simple.aop.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import simple.aop.trace.strategy.code.strategy.ContextV1;
import simple.aop.trace.strategy.code.strategy.Strategy;
import simple.aop.trace.strategy.code.strategy.StrategyLogic1;
import simple.aop.trace.strategy.code.strategy.StrategyLogic2;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
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
    void strategyV1() {
        StrategyLogic1 logic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1((logic1));
        context1.execute();

        StrategyLogic2 logic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1((logic2));
        context2.execute();
    }

    @Test
    void strategyV2() {
        Strategy logic1 = new Strategy() {
            @Override
            public void call() {
                log.info("로직1 실행");
            }
        };

        ContextV1 context = new ContextV1(logic1);
        log.info("전략 로직={}", logic1.getClass());
        context.execute();

        Strategy logic2 = new Strategy() {
            @Override
            public void call() {
                log.info("로직2 실행");
            }
        };

        ContextV1 context2 = new ContextV1(logic2);
        log.info("전략 로직2={}", logic2.getClass());
        context2.execute();
    }

    @Test
    void strategyV3() {

        ContextV1 context = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("로직1 실행");
            }
        });
        context.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("로직2 실행");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV4() {

        ContextV1 context = new ContextV1(() -> log.info("로직1 실행"));
        context.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("로직2 실행"));
        context2.execute();
    }

}
