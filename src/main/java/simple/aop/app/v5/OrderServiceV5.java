package simple.aop.app.v5;

import org.springframework.stereotype.Service;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.callback.TraceCallback;
import simple.aop.trace.callback.TraceTemplate;
import simple.aop.trace.logtrace.LogTrace;
import simple.aop.trace.template.AbstractTemplate;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute ("OrderService.request()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
