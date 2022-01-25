package simple.aop.trace.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import simple.aop.trace.TraceId;
import simple.aop.trace.TraceStatus;


@Slf4j
@Component
public class SimpleTraceV0 {
    private static final String START_PREFIX ="-->";
    private static final String COMPLETE_PREFIX ="<--";
    private static final String EX_PREFIX ="<X-";

    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }
    public void end(TraceStatus status) {
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId tracdId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={}ms", tracdId.getId(), addSpace(COMPLETE_PREFIX, tracdId.getLevel()), status.getMessage(), resultTimeMs);
        }
        else  {
            log.info("[{}] {}{} time={}ms ex={}", tracdId.getId(), addSpace(EX_PREFIX, tracdId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }
}