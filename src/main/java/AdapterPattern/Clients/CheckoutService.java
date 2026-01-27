package AdapterPattern.Clients;

import AdapterPattern.Adapters.PaymentGateway;

import java.math.BigDecimal;

public class CheckoutService {

    private final PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(BigDecimal amount) {
        paymentGateway.processPayment(amount);
    }
}
