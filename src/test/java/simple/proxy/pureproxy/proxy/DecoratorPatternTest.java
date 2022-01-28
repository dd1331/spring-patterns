package simple.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;
import simple.proxy.pureproxy.decorator.code.*;

public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component component = new RealComponent();

        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }

    @Test
    void decorator1() {

        Component component = new RealComponent();

        Component messageDecorator = new MessageDecorator(component);

        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }


    @Test
    void decorator2() {

        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
