package Problems.ecommerce.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private final Map<String, CartItem> itemsByProductId = new LinkedHashMap<>();

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        CartItem existing = itemsByProductId.get(product.getId());
        if (existing == null) {
            itemsByProductId.put(product.getId(), new CartItem(product, quantity));
        } else {
            existing.addQuantity(quantity);
        }
    }

    public void removeProduct(String productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        CartItem existing = itemsByProductId.get(productId);
        if (existing == null) {
            return;
        }
        int next = existing.getQuantity() - quantity;
        if (next <= 0) {
            itemsByProductId.remove(productId);
        } else {
            existing.addQuantity(-quantity);
        }
    }

    public Collection<CartItem> getItems() {
        return Collections.unmodifiableCollection(itemsByProductId.values());
    }

    public boolean isEmpty() {
        return itemsByProductId.isEmpty();
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : itemsByProductId.values()) {
            total = total.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

    public void clear() {
        itemsByProductId.clear();
    }
}
