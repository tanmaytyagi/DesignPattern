package Problems.RateLimiter;

import Problems.RateLimiter.service.RateLimiterService;
import Problems.RateLimiter.strategies.FixedWindowStrategy;
import Problems.RateLimiter.strategies.RateLimitingStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String userId = "user123";

        int maxRequests = 5;
        int windowSeconds = 10;

        RateLimitingStrategy fixedWindowStrategy = new FixedWindowStrategy(maxRequests, windowSeconds);
        RateLimiterService service = RateLimiterService.getInstance();
        service.setRateLimitingStrategy(fixedWindowStrategy);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> service.handleRequest(userId));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
    }
}
