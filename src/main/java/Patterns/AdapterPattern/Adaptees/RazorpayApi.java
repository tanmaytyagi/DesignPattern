package Patterns.AdapterPattern.Adaptees;

public class RazorpayApi {

    public void pay(double amountInRupees) {
        System.out.println("Razorpay payment of " + amountInRupees);
    }
}
