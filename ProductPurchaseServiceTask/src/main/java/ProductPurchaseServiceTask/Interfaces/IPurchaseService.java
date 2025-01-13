package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPurchaseService {

    /**
     * Adds new product to product catalog
     * @param productId new Product id
     * @param price price of the product
     */
    void addNewProduct(int productId, double price, String name);

    /**
     * Removes product from catalog
     * @param product Product to be removed from catalog
     */
    void removeProduct(int productId);

    /**
     * Returns collection of products currently available for purchase
     * @return List of available products
     */
    Map<Integer, IProduct> getAvailableProducts();

    /**
     * Purchases a product
     * @param product Product to be purchased
     */
    void purchaseProduct(IProduct product);

    /**
     * Returns a sales report for a given timeframe
     * @param fromDate Start date for sales report
     * @param toDate End date for sales report
     * @return ISalesReport containing sales information
     */
    ISalesReport getSalesReport(Date fromDate, Date toDate);
}