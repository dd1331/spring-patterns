package simple.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import simple.aop.trace.logtrace.LogTrace;
import simple.aop.trace.logtrace.ThreadLocalLogTrace;
import simple.proxy.config.AppV1Config;
import simple.proxy.config.AppV2Config;
import simple.proxy.config.v1_proxy.ConcreteProxyConfig;
import simple.proxy.config.v1_proxy.InterfaceProxyConfig;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
@Import(ConcreteProxyConfig.class)
@SpringBootApplication(scanBasePackages = "simple.proxy.app")
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
