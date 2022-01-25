package simple.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.aop.trace.logtrace.FieldLogTrace;
import simple.aop.trace.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
