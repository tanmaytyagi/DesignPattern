package Problems.ecommerce.payment;

public interface PaymentGateway {
    String getName();

    PaymentResult pay(PaymentRequest request);
}
