package demo;

public class LegacyReceiptFormatter {
    public String format(String orderId) {
        return "Receipt-v2:" + orderId;
    }
}
