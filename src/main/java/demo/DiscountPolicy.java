package demo;

public class DiscountPolicy {
    public int discount(int subtotal) {
        if (subtotal < 0) throw new IllegalArgumentException("negative subtotal");
        return subtotal * 12 / 100;
    }
}
