package AdapterPattern.Adaptees;

public class StripeApi {

    public void charge(double dollars) {
        System.out.println("Stripe payment of $" + dollars);
    }
}