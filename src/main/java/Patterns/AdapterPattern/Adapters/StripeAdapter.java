package Patterns.AdapterPattern.Adapters;

import Patterns.AdapterPattern.Adaptees.StripeApi;

import java.math.BigDecimal;

public class StripeAdapter implements PaymentGateway {

    private final StripeApi stripeApi;

    public StripeAdapter(StripeApi stripeApi) {
        this.stripeApi = stripeApi;
    }

    @Override
    public void processPayment(BigDecimal amount) {
        stripeApi.charge(amount.doubleValue());
    }
}