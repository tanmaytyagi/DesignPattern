package Problems.RateLimiter.strategies;

public interface RateLimitingStrategy {
    boolean allowRequest(String userId);
}
