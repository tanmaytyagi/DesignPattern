package Problems.RateLimiter.model;

public class TokenBucket {
    public int tokens;
    final int capacity;
    final int refillRatePerSecond;
    long lastRefillTimestamp;

    public TokenBucket(int capacity, int refillRatePerSecond, long currentTimeMillis) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillTimestamp = currentTimeMillis;
    }

    public void refill(long currentTime) {
        long elapsedTime = currentTime - lastRefillTimestamp;
        int tokensToAdd = (int) ((elapsedTime / 1000.0) * refillRatePerSecond);

        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimestamp = currentTime;
        }
    }
}
