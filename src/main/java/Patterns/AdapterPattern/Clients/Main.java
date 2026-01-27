package Patterns.AdapterPattern.Clients;

import Patterns.AdapterPattern.Adaptees.PaytmApi;
import Patterns.AdapterPattern.Adaptees.RazorpayApi;
import Patterns.AdapterPattern.Adaptees.StripeApi;
import Patterns.AdapterPattern.Adapters.PaymentGateway;
import Patterns.AdapterPattern.Adapters.PaytmAdapter;
import Patterns.AdapterPattern.Adapters.RazorpayAdapter;
import Patterns.AdapterPattern.Adapters.StripeAdapter;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        PaymentGateway paytm =
                new PaytmAdapter(new PaytmApi());

        PaymentGateway razorpay =
                new RazorpayAdapter(new RazorpayApi());

        PaymentGateway stripe =
                new StripeAdapter(new StripeApi());

        CheckoutService service = new CheckoutService(paytm);
        service.checkout(BigDecimal.valueOf(500));

        service = new CheckoutService(razorpay);
        service.checkout(BigDecimal.valueOf(800));

        service = new CheckoutService(stripe);
        service.checkout(BigDecimal.valueOf(10));
    }
}
