package ProductPurchaseServiceTask.Interfaces;

public interface ISoldProductSummary {
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
    String getProductId();
}
