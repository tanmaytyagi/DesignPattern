package Patterns.AdapterPattern.Adapters;

import Patterns.AdapterPattern.Adaptees.PaytmApi;

import java.math.BigDecimal;

public class PaytmAdapter implements PaymentGateway {

    private final PaytmApi paytmApi;

    public PaytmAdapter(PaytmApi paytmApi) {
        this.paytmApi = paytmApi;
    }

    @Override
    public void processPayment(BigDecimal amount) {
        paytmApi.makePayment(amount.doubleValue());
    }
}
