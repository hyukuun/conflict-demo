package demo;

public class ShopSelfTest {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        var orders = new OrderService();
        var discounts = new DiscountPolicy();
        check(orders.shippingFee(200000) == 0, "large orders ship free");
        check(orders.shippingFee(10000) > 0, "small orders pay shipping");
        check(orders.checkoutTotal(10000) == 10000 - discounts.discount(10000) + orders.shippingFee(10000),
                "checkout adds discounted subtotal and shipping");
        check(orders.discountedSubtotal(10000) >= 0, "discount cannot exceed subtotal");
        check(new InventoryController().checkout("BOOK", 2), "valid inventory reservation");
        check(!new InventoryController().checkout("BOOK", 0), "zero quantity rejected");
        try {
            orders.shippingFee(-1);
            throw new AssertionError("negative subtotal must fail");
        } catch (IllegalArgumentException expected) { }
        System.out.println("SHOP SELF TEST PASS");
    }
}
