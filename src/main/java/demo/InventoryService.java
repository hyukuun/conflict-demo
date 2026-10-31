package demo;

public class InventoryService {
    public boolean reserve(String sku, int quantity) {
        return sku != null && !sku.isBlank() && quantity > 0;
    }
}
