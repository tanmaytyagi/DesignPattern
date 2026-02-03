package Problems.ecommerce.payment;

import java.util.UUID;

public class StripeGateway implements PaymentGateway {
    @Override
    public String getName() {
        return "stripe";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        return new PaymentResult(true, "stripe-" + UUID.randomUUID());
    }
}
