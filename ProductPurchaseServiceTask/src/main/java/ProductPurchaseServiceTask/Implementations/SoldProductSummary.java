package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.util.Date;

public class SoldProductSummary implements ISoldProductSummary {
    private final String productName;
    private final int quantity;
    private final double totalPrice;
    private final String productId;

    public SoldProductSummary(String productName, int quantity, double totalPrice, String productId) {

        if (totalPrice < 0 || quantity < 0) {
            throw new IllegalArgumentException("ERROR ⚠: Price and quantity must be non-negative.");
        }

        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.productId = productId;

    }

    public String getProductName() {
        return productName;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public int getSoldAmount() {
        return quantity;
    }
}