package simple.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;
import simple.proxy.pureproxy.proxy.code.CacheProxy;
import simple.proxy.pureproxy.proxy.code.ProxyPatternClient;
import simple.proxy.pureproxy.proxy.code.RealSubject;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {

        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(new CacheProxy(realSubject));
        client.execute();
        client.execute();
        client.execute();


    }
}
