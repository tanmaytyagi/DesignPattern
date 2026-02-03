package Problems.ecommerce.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {
    private final Map<String, Integer> stockByProductId = new ConcurrentHashMap<>();

    public void setStock(String productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity cannot be negative");
        }
        stockByProductId.put(productId, quantity);
    }

    public int getStock(String productId) {
        return stockByProductId.getOrDefault(productId, 0);
    }

    public synchronized boolean reserveAndDeduct(Map<String, Integer> requested) {
        for (Map.Entry<String, Integer> entry : requested.entrySet()) {
            int available = getStock(entry.getKey());
            int needed = entry.getValue();
            if (needed <= 0 || available < needed) {
                return false;
            }
        }
        for (Map.Entry<String, Integer> entry : requested.entrySet()) {
            String productId = entry.getKey();
            int available = getStock(productId);
            stockByProductId.put(productId, available - entry.getValue());
        }
        return true;
    }

    public synchronized void restore(Map<String, Integer> quantities) {
        for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
            String productId = entry.getKey();
            int available = getStock(productId);
            stockByProductId.put(productId, available + entry.getValue());
        }
    }
}
