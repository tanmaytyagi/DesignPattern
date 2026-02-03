package Problems.ecommerce;

import com.project.ecommerce.model.Category;
import com.project.ecommerce.model.Order;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.payment.PayPalGateway;
import com.project.ecommerce.payment.StripeGateway;
import com.project.ecommerce.repository.InMemoryOrderRepository;
import com.project.ecommerce.repository.InMemoryProductRepository;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.service.CartService;
import com.project.ecommerce.service.InventoryService;
import com.project.ecommerce.service.OrderService;
import com.project.ecommerce.service.PaymentService;
import com.project.ecommerce.service.ProductCatalogService;

import java.math.BigDecimal;
import java.util.List;

public class EcommerceDemo {
    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();
        InventoryService inventoryService = new InventoryService();
        ProductCatalogService catalogService = new ProductCatalogService(productRepository);
        CartService cartService = new CartService();
        PaymentService paymentService = new PaymentService(List.of(
            new StripeGateway(),
            new PayPalGateway()
        ));
        OrderService orderService = new OrderService(cartService, inventoryService, paymentService, orderRepository);

        Product phone = new Product("p1", "Phone X", Category.ELECTRONICS, "Flagship phone", new BigDecimal("699.00"));
        Product book = new Product("p2", "Clean Code", Category.BOOKS, "Classic programming book", new BigDecimal("35.00"));

        catalogService.addProduct(phone);
        catalogService.addProduct(book);
        inventoryService.setStock("p1", 10);
        inventoryService.setStock("p2", 3);

        cartService.addItem("user-1", phone, 1);
        cartService.addItem("user-1", book, 2);

        Order order = orderService.placeOrder("user-1", "stripe");
        System.out.println("Order confirmed: " + order.getId() + " total=" + order.getTotal());
        System.out.println("Past orders: " + orderService.getOrdersForUser("user-1").size());
    }
}
