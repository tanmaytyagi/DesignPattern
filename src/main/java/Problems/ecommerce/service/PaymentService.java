package Problems.ecommerce.service;

import com.project.ecommerce.payment.PaymentGateway;
import com.project.ecommerce.payment.PaymentRequest;
import com.project.ecommerce.payment.PaymentResult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PaymentService {
    private final Map<String, PaymentGateway> gateways = new HashMap<>();

    public PaymentService(Iterable<PaymentGateway> gatewayList) {
        for (PaymentGateway gateway : gatewayList) {
            gateways.put(gateway.getName(), gateway);
        }
    }

    public PaymentResult pay(String gatewayName, String userId, BigDecimal amount) {
        Objects.requireNonNull(gatewayName, "gatewayName");
        PaymentGateway gateway = gateways.get(gatewayName);
        if (gateway == null) {
            throw new IllegalArgumentException("Unknown payment gateway: " + gatewayName);
        }
        return gateway.pay(new PaymentRequest(userId, amount));
    }
}
