package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;
import java.util.List;

public interface IPurchaseService {
    /**
     * Adds new product to product catalog
     * @param product new Product type
     */
    void addNewProduct(String productId, double price, String name);

    /**
     * Removes product from catalog
     * @param product Product to be removed from catalog
     */
    void removeProduct(IProduct product);
    /**
     * Returns collection of products currenctly available for purchase
     */
    List<IProduct> getAvailableProducts();
    /**
     * Purchases new product
     * @param product Product to be purchased
     */
    void purchaseProduct(IProduct product);

    /**
     * Returns ISalesReport which contains sales done in given time periods.
     * @param fromDate From date, which from the sales should be counted from
     * @param toDate To date, which to sales should be counted to
     * @return ISalesReport which contaisn total summary of all sold products and their amounts and total sales.
     */
    ISalesReport getSalesReport(Date fromDate, Date toDate);
}