package Problems.ecommerce.payment;

import java.util.UUID;

public class PayPalGateway implements PaymentGateway {
    @Override
    public String getName() {
        return "paypal";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        return new PaymentResult(true, "paypal-" + UUID.randomUUID());
    }
}
