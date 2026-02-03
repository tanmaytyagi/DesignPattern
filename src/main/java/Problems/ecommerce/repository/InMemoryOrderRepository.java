package Problems.ecommerce.repository;

import com.project.ecommerce.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, List<Order>> ordersByUser = new ConcurrentHashMap<>();

    @Override
    public void save(Order order) {
        ordersByUser.computeIfAbsent(order.getUserId(), key -> new ArrayList<>()).add(order);
    }

    @Override
    public List<Order> findByUserId(String userId) {
        return ordersByUser.getOrDefault(userId, List.of());
    }
}
