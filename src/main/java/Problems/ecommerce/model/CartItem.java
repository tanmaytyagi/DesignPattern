package Problems.ecommerce.model;

import java.util.Objects;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = Objects.requireNonNull(product, "product");
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int delta) {
        int next = this.quantity + delta;
        if (next <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        this.quantity = next;
    }
}
