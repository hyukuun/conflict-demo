package demo;

public class CheckoutNotes {
    public String note(String orderId) {
        if (orderId == null) throw new IllegalArgumentException("missing order");
        return "Created:" + orderId;
    }
}
