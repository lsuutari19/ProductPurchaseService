package ProductPurchaseServiceTask;

import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Implementations.PurchaseService;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Utils.Utils;


import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nStarting the application!");

        IPurchaseService purchaseService = new PurchaseService();

        System.out.println("\nCreating placeholder products...");
        purchaseService.addNewProduct(1, 5.00, "MOVIE TICKET");
        purchaseService.addNewProduct(2, 2.00, "SODA");
        purchaseService.addNewProduct(3, 6.00, "MOVIE TICKET and SODA");
        purchaseService.addNewProduct(4, 3.00, "POPCORN");
        purchaseService.addNewProduct(5, 10.00, "DELUXE COMBO");

        System.out.println("\nPurchasing products for testing purposes...");
        IProduct movieTicket = purchaseService.getAvailableProducts().get(1);
        IProduct soda = purchaseService.getAvailableProducts().get(2);
        purchaseService.purchaseProduct(movieTicket);

        try {
            // Sleep just to get a different purchase time for testing purposes
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted: " + e.getMessage());
        }

        purchaseService.purchaseProduct(soda);

        Utils.displayAvailableProducts(purchaseService.getAvailableProducts());

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

        //Utils.displaySoldProducts(salesReport, fromDate, toDate);
        Utils.displaySalesReport(salesReport, fromDate, toDate);

        System.out.println("\nRemoving product with productId 5...");
        purchaseService.removeProduct(5);
        Utils.displayAvailableProducts(purchaseService.getAvailableProducts());

        // Start the demo CLI app
        Utils.cliDemo(purchaseService);
    }
}
