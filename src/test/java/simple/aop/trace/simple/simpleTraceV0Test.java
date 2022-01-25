package simple.aop.trace.simple;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import simple.aop.trace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;


class simpleTraceV0Test {
    @Test
    void begin_end() {
        SimpleTraceV0 trace = new SimpleTraceV0();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        SimpleTraceV0 trace = new SimpleTraceV0();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}