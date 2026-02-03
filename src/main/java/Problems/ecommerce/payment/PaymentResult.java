package Problems.ecommerce.payment;

public class PaymentResult {
    private final boolean success;
    private final String referenceId;

    public PaymentResult(boolean success, String referenceId) {
        this.success = success;
        this.referenceId = referenceId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getReferenceId() {
        return referenceId;
    }
}
