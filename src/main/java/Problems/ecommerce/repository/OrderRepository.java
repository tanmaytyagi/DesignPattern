package Problems.ecommerce.repository;

import com.project.ecommerce.model.Order;

import java.util.List;

public interface OrderRepository {
    void save(Order order);

    List<Order> findByUserId(String userId);
}
