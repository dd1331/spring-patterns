package simple.proxy.app.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import simple.proxy.app.v2.OrderServiceV2;


@Slf4j
@RestController
public class OrderControllerV3 {

    private final OrderServiceV3 service;

    public OrderControllerV3(OrderServiceV3 service) {
        this.service = service;
    }


    @GetMapping("proxy/v3/request")
    public String request(String itemId) {
        service.orderItem(itemId);
        return "ok";
    }

    @GetMapping("proxy/v3/no-log")
    public String noLog() {
        return "ok";
    }
}
