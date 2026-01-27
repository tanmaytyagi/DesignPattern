package AdapterPattern.Clients;

import AdapterPattern.Adaptees.PaytmApi;
import AdapterPattern.Adaptees.RazorpayApi;
import AdapterPattern.Adaptees.StripeApi;
import AdapterPattern.Adapters.PaymentGateway;
import AdapterPattern.Adapters.PaytmAdapter;
import AdapterPattern.Adapters.RazorpayAdapter;
import AdapterPattern.Adapters.StripeAdapter;

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
