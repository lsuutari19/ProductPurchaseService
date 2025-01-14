package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.IProduct;

import java.util.Date;

public class Product implements IProduct {
    private final int productId;
    private final double price;
    private final String name;
    private Date purchaseDate;

    public Product(int productId, double price, String name) {
        if (price < 0) {
            throw new IllegalArgumentException("ERROR ⚠: Price can't be set to negative number!");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("ERROR ⚠: Name can't be null!");
        }
        if (productId < 0) {
            throw new IllegalArgumentException("ERROR ⚠: ProductId can't be set to negative number!");
        }

        this.productId = productId;
        this.price = price;
        this.name = name;
        this.purchaseDate = null;

    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    // Get a string representation of the product details for debug purposes instead of the memory reference
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
