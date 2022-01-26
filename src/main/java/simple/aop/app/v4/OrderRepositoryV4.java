package simple.aop.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;
import simple.aop.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        TraceStatus status = null;

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }
                sleep(1000);
                return null;
            }
        };

        template.execute("OrderRepository.request()");

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
