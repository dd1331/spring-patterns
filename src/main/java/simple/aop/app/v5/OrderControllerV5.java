package simple.aop.app.v5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.aop.trace.callback.TraceCallback;
import simple.aop.trace.callback.TraceTemplate;
import simple.aop.trace.logtrace.LogTrace;
import simple.aop.trace.template.AbstractTemplate;


@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {

        return template.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });
//        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
//            @Override
//            protected String call() {
//                orderService.orderItem(itemId);
//                return "ok";
//            }
//        };

    }
}
