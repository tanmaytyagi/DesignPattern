package Problems.ecommerce.service;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.Order;
import com.project.ecommerce.model.OrderItem;
import com.project.ecommerce.model.OrderStatus;
import com.project.ecommerce.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class OrderService {
    private final CartService cartService;
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final OrderRepository orderRepository;

    public OrderService(CartService cartService,
                        InventoryService inventoryService,
                        PaymentService paymentService,
                        OrderRepository orderRepository) {
        this.cartService = Objects.requireNonNull(cartService, "cartService");
        this.inventoryService = Objects.requireNonNull(inventoryService, "inventoryService");
        this.paymentService = Objects.requireNonNull(paymentService, "paymentService");
        this.orderRepository = Objects.requireNonNull(orderRepository, "orderRepository");
    }

    public Order placeOrder(String userId, String paymentGateway) {
        Cart cart = cartService.getCart(userId);
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Map<String, Integer> required = new HashMap<>();
        for (CartItem item : cart.getItems()) {
            required.put(item.getProduct().getId(), item.getQuantity());
        }

        if (!inventoryService.reserveAndDeduct(required)) {
            throw new IllegalStateException("Insufficient inventory");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            orderItems.add(new OrderItem(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity()
            ));
        }

        BigDecimal total = cart.getTotal();
        try {
            if (!paymentService.pay(paymentGateway, userId, total).isSuccess()) {
                inventoryService.restore(required);
                throw new IllegalStateException("Payment failed");
            }
        } catch (RuntimeException ex) {
            inventoryService.restore(required);
            throw ex;
        }

        Order order = new Order(
            UUID.randomUUID().toString(),
            userId,
            orderItems,
            total,
            OrderStatus.CONFIRMED,
            Instant.now()
        );
        orderRepository.save(order);
        cartService.clear(userId);
        return order;
    }

    public List<Order> getOrdersForUser(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
