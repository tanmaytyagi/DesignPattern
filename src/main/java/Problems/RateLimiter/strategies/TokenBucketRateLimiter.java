package Problems.RateLimiter.strategies;

import Problems.RateLimiter.model.TokenBucket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimitingStrategy {

    private final int capacity;
    private final int refillRatePerSecond;
    private final Map<String, TokenBucket> userBuckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userBuckets.putIfAbsent(userId, new TokenBucket(capacity, refillRatePerSecond, currentTime));
        TokenBucket bucket = userBuckets.get(userId);

        synchronized (bucket) {
            bucket.refill(currentTime);
            if (bucket.tokens > 0) {
                bucket.tokens--;
                return true;
            } else {
                return false;
            }
        }
    }
}
