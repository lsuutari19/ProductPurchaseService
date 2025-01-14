package ProductPurchaseServiceTask;

import ProductPurchaseServiceTask.Implementations.Product;
import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Implementations.PurchaseService;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

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

        Scanner sc = new Scanner(System.in);
        String choice = "";
        while(!Objects.equals(choice, "q")) {
            System.out.println("\nPlease choose an option:");
            System.out.println("  1. Add new product");
            System.out.println("  2. Purchase product");
            System.out.println("  3. List all available products");
            System.out.println("  4. Remove product");
            System.out.println("  q. Exit");
            choice = sc.nextLine();
            System.out.println(choice);

            if(Objects.equals(choice, "1")) {
                try {
                    System.out.println("\nEnter name of new product: ");
                    String productName = sc.nextLine();

                    System.out.println("\nEnter product price: ");
                    int productPrice = sc.nextInt();

                    System.out.println("\nEnter product ID: ");
                    int productId = sc.nextInt();
                    purchaseService.addNewProduct(productId, productPrice, productName);
                } catch (Exception e) {
                    System.out.println("ERROR ⚠: " + e);
                }


            }
            if(Objects.equals(choice, "2")) {
                continue;
            }
            if(Objects.equals(choice, "3")) {
                System.out.println("\nAll available products are: " + purchaseService.getAvailableProducts());
            }
            if(Objects.equals(choice, "4")) {
                try {
                    System.out.println("Enter product ID that is to be removed, available products are: " + purchaseService.getAvailableProducts());
                    int productId = sc.nextInt();
                    purchaseService.removeProduct(productId);
                } catch(Exception e) {
                    System.out.println("ERROR ⚠: " + e);
                }
            }
        }
    }
}
