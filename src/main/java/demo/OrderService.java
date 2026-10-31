package demo;

public class OrderService {
    private final DiscountPolicy discounts = new DiscountPolicy();

    public int shippingFee(int subtotal) {
        if (subtotal < 0) throw new IllegalArgumentException("negative subtotal");
        return subtotal >= 150000 ? 0 : 3000;
    }

    public int discountedSubtotal(int subtotal) {
        if (subtotal < 0) throw new IllegalArgumentException("negative subtotal");
        return subtotal - discounts.discount(subtotal);
    }

    public int checkoutTotal(int subtotal) {
        return discountedSubtotal(subtotal) + shippingFee(subtotal);
    }

    public boolean canCancel(String status) {
        return "READY".equals(status);
    }

    public String orderStatus() {
        return "READY";
    }
}
