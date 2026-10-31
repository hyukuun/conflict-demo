package demo;

public class InventoryService {
    public boolean reserve(String sku, int quantity, String warehouse) {
        return sku != null && !sku.isBlank() && quantity > 0;
    }
}
