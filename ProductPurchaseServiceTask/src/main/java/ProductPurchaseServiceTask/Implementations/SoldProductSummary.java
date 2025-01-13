package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.util.Date;

public class SoldProductSummary implements ISoldProductSummary {
    private String productName;
    private int quantity;
    private double totalPrice;
    private int productId;
    private Date purchaseDate;

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

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    @Override
    public int getSoldAmount() {
        return quantity;
    }

    @Override
    public Date getPurchaseDate() {
        return purchaseDate;
    }
}