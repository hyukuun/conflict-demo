package demo;

public class CheckoutNotes {
    public String note(String orderId) {
        if (orderId.isBlank()) throw new IllegalArgumentException("blank order");
        return "Created:" + orderId;
    }
}
