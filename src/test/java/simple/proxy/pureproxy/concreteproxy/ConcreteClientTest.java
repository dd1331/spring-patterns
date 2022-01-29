package simple.proxy.pureproxy.concreteproxy;

import org.junit.jupiter.api.Test;
import simple.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import simple.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import simple.proxy.pureproxy.concreteproxy.code.TimeProxy;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteClientTest {

    @Test
    void noProxy() {
        ConcreteLogic logic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(logic);
        client.execute();
    }

    @Test
    void proxy() {
        ConcreteLogic logic = new ConcreteLogic();
        ConcreteLogic timeDecorator = new TimeProxy(logic);
        ConcreteClient client = new ConcreteClient(timeDecorator);

        client.execute();
    }

}