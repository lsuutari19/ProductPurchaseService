package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.util.Date;

public class SoldProductSummary implements ISoldProductSummary {
    private final String productName;
    private final int quantity;
    private final double totalPrice;
    private final int productId;
    private final Date purchaseDate;

    public SoldProductSummary(String productName, int quantity, double totalPrice, int productId, Date purchaseDate) {

        if (totalPrice < 0 || quantity < 0) {
            throw new IllegalArgumentException("ERROR ⚠: Price and quantity must be non-negative.");
        }

        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    public String getProductName() {
        return productName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getSoldAmount() {
        return quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }
}