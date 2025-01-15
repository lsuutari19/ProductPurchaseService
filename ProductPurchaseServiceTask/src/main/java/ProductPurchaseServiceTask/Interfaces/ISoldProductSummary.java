package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;

public interface ISoldProductSummary {
    /**
     * Name of the product sold
     * @return string value of product name
     */
    String getProductName();

    /**
     * Total amount of this kind of products sold
     * @return int value of amount of this product sold
     */
    int getSoldAmount();

    /**
     * Total sum of price value of sold items.
     * @return double value of sales of this product
     */
    double getTotalPrice();

    /**
     * Product id
     * @return product id of this product summary
     */
    int getProductId();

    /**
     * Product purchase date
     * @return Date value of when this product was purchased
     */
    Date getPurchaseDate();
}
