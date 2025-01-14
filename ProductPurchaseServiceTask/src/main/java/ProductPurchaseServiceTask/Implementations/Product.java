package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.IProduct;

import java.util.Date;

public class Product implements IProduct {
    private final String productId;
    private final double price;
    private final String name;
    private Date purchaseDate;

    public Product(String productId, double price, String name) {
        if (price < 0) {
            throw new IllegalArgumentException("ERROR ⚠: Price can't be set to negative number!");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("ERROR ⚠: Name can't be null!");
        }
        if (productId == null) {
            throw new IllegalArgumentException("ERROR ⚠: ProductId can't be set to null!");
        }
        if (!name.matches("^[a-zA-Z0-9 -]+$")) {
            throw new IllegalArgumentException("ERROR ⚠: Name contains invalid characters!");
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
    public String getProductId() {
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

    // Get a string representation of the product details for human readability instead of the memory reference
    public String toString() {
        return String.format(
                "Product ID: %-5s | Price: %-10.2f | Name: %s",
                productId, price, name
        );
    }
}
