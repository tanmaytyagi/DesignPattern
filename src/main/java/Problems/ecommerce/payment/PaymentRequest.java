package Problems.ecommerce.payment;

import java.math.BigDecimal;
import java.util.Objects;

public class PaymentRequest {
    private final String userId;
    private final BigDecimal amount;

    public PaymentRequest(String userId, BigDecimal amount) {
        this.userId = Objects.requireNonNull(userId, "userId");
        this.amount = Objects.requireNonNull(amount, "amount");
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
