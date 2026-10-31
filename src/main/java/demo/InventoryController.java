package demo;

public class InventoryController {
    public boolean checkout(String sku, int quantity) {
        return new InventoryService().reserve(sku, quantity);
    }
}
