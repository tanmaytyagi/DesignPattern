package AdapterPattern.Adaptees;

public class PaytmApi {

    public void makePayment(double amount) {
        System.out.println("Paytm payment of " + amount);
    }
}
