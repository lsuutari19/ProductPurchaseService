package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;

public interface IProduct {
    /**
     * Price of this product
     * @return price of this product
     */
    double getPrice();
    /**
     * Product id of this product. Should be unique string.
     * @return Product id of this product
     */
    String getProductId();

    String getName();
}
