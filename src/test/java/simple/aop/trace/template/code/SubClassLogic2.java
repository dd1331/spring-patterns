package simple.aop.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractTemplate {

    @Override
    protected void call() {
        log.info("로직2 실행");
    }
}
