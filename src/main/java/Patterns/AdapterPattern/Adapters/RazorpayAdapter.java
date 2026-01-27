package Patterns.AdapterPattern.Adapters;

import Patterns.AdapterPattern.Adaptees.RazorpayApi;

import java.math.BigDecimal;

public class RazorpayAdapter implements PaymentGateway {

    private final RazorpayApi razorpayApi;

    public RazorpayAdapter(RazorpayApi razorpayApi) {
        this.razorpayApi = razorpayApi;
    }

    @Override
    public void processPayment(BigDecimal amount) {
        razorpayApi.pay(amount.doubleValue());
    }
}
