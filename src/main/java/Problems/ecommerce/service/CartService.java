package Problems.ecommerce.service;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.Product;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CartService {
    private final Map<String, Cart> cartsByUser = new ConcurrentHashMap<>();

    public Cart getCart(String userId) {
        return cartsByUser.computeIfAbsent(userId, id -> new Cart());
    }

    public void addItem(String userId, Product product, int quantity) {
        Objects.requireNonNull(product, "product");
        getCart(userId).addProduct(product, quantity);
    }

    public void removeItem(String userId, String productId, int quantity) {
        getCart(userId).removeProduct(productId, quantity);
    }

    public void clear(String userId) {
        getCart(userId).clear();
    }
}
