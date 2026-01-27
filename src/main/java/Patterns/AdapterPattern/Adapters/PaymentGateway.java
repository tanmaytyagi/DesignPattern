package Patterns.AdapterPattern.Adapters;

import java.math.BigDecimal;

public interface PaymentGateway {
    void processPayment(BigDecimal amount);
}
