package simple.aop.app.v5;

import org.springframework.stereotype.Repository;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.callback.TraceCallback;
import simple.aop.trace.callback.TraceTemplate;
import simple.aop.trace.logtrace.LogTrace;
import simple.aop.trace.template.AbstractTemplate;

@Repository
public class OrderRepositoryV5 {

    private final LogTrace trace;
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace, TraceTemplate template) {
        this.trace = trace;
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId) {

        template.execute("OrderRepository.request()", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
