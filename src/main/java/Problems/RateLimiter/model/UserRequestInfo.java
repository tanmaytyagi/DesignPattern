package Problems.RateLimiter.model;

import java.util.concurrent.atomic.AtomicInteger;

public class UserRequestInfo {
    public long windowStart;
    public AtomicInteger requestCount;

    public UserRequestInfo(long startTime) {
        this.windowStart = startTime;
        this.requestCount = new AtomicInteger(0);
    }

    public void reset(long newStart) {
        this.windowStart = newStart;
        this.requestCount.set(0);
    }
}
