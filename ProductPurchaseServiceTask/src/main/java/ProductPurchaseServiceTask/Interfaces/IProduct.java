package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;

public interface IProduct {
    /**
     * Price of this product
     * @return price of this product
     */
    double getPrice();

    /**
     * Product id of this product. Should be unique.
     * @return Product id of this product
     */
    int getProductId();

    /**
     * Product name of this product.
     * @return Product name of this product
     */
    String getName();

    /**
     * Purchase date of this product.
     * @return Purchase date of this product
     */
    void setPurchaseDate(Date date);
    Date getPurchaseDate();
}
