package simple.aop.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.simple.SimpleTraceV1;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private TraceStatus status;
    private final SimpleTraceV1 simpleTraceV1;

    public void save(String itemId) {
        status = simpleTraceV1.begin("OrderRepository.save()");
        try {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            sleep(1000);
            simpleTraceV1.end(status);

        } catch (Exception e) {
            simpleTraceV1.exception(status, e);
            throw e;
        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
