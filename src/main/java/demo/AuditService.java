package demo;

/** Formats audit events. Intentionally independent of the checkout caller. */
public class AuditService {
    public String event(String orderId) {
        if (orderId == null) throw new IllegalArgumentException("missing order");
        return "CHECKOUT:" + orderId;
    }

    public String category() {
        return "checkout";
    }

    public boolean enabled() {
        return true;
    }
}
