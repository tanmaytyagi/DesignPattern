package Problems.ecommerce.model;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {
    private final String productId;
    private final String title;
    private final BigDecimal price;
    private final int quantity;

    public OrderItem(String productId, String title, BigDecimal price, int quantity) {
        this.productId = Objects.requireNonNull(productId, "productId");
        this.title = Objects.requireNonNull(title, "title");
        this.price = Objects.requireNonNull(price, "price");
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getLineTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
