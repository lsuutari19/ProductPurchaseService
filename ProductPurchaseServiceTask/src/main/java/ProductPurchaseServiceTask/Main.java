package ProductPurchaseServiceTask;

import ProductPurchaseServiceTask.Implementations.Product;
import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Implementations.PurchaseService;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nStarting the application!");

        IPurchaseService purchaseService = new PurchaseService();

        System.out.println("\nCreating placeholder products...");
        purchaseService.addNewProduct(001, 5.00, "MOVIE TICKET");
        purchaseService.addNewProduct(002, 2.00, "SODA");

        System.out.println("\nPurchasing products for testing purposes...");
        IProduct movieTicket = purchaseService.getAvailableProducts().get(1);
        IProduct soda = purchaseService.getAvailableProducts().get(2);
        purchaseService.purchaseProduct(movieTicket);
        purchaseService.purchaseProduct(soda);

        System.out.println("\nAll available products are now: " + purchaseService.getAvailableProducts());

        System.out.println("\nVerifying purchased products and their purchase dates...");
        for (IProduct purchasedProduct : ((PurchaseService) purchaseService).getPurchasedProducts()) {
            System.out.println("Product Name: " + purchasedProduct.getName());
            System.out.println("Purchase Date: " + purchasedProduct.getPurchaseDate());
        }

        long currentTime = System.currentTimeMillis();
        long oneDayAgo = currentTime - 24 * 60 * 60 * 1000;

        Date fromDate = new Date(oneDayAgo);
        Date toDate = new Date(currentTime);
        ISalesReport salesReport = purchaseService.getSalesReport(fromDate, toDate);

        System.out.println("\nSold products between " + fromDate + " and " + toDate + ": ");
        for (ISoldProductSummary summary : salesReport.getSoldProducts()) {
            System.out.println("Product Name: " + summary.getProductName());
            System.out.println("Quantity Sold: " + summary.getSoldAmount());
            System.out.println("Total Sales: " + summary.getTotalPrice());
            System.out.println("Product ID: " + summary.getProductId());
            System.out.println("Purchase Date: " + summary.getPurchaseDate());
        }

        System.out.println("\nRemoving product with productId 001...");
        purchaseService.removeProduct(001);
        System.out.println("\nAll available products are now: " + purchaseService.getAvailableProducts());

    }
}
