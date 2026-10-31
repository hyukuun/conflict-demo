package demo;

public class InventoryBatchController {
    public boolean reserveBatch(String sku, int quantity) {
        return new InventoryService().reserve(sku, quantity);
    }
}
