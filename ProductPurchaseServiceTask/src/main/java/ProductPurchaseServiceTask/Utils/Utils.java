package ProductPurchaseServiceTask.Utils;

import ProductPurchaseServiceTask.Implementations.PurchaseService;
import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    public static void displayAvailableProducts(Map<Integer, IProduct> products) {
        // Used to iterate the product data in to human readable format
        if (products == null || products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\nAll available products are now:");
        for (Map.Entry<Integer, IProduct> entry : products.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public static void displaySoldProducts(ISalesReport salesReport, Date fromDate, Date toDate) {
        // Used to iterate the sales data in to human readable format
        System.out.println("\nSold products between " + fromDate + " and " + toDate + ": ");
        for (ISoldProductSummary summary : salesReport.getSoldProducts()) {
            System.out.println("Product Name: " + summary.getProductName());
            System.out.println("Quantity Sold: " + summary.getSoldAmount());
            System.out.println("Total Sales: " + summary.getTotalPrice());
            System.out.println("Product ID: " + summary.getProductId());
            System.out.println("Purchase Date: " + summary.getPurchaseDate() + "\n");
        }
    }

    public static void displaySalesReport(ISalesReport salesReport, Date fromDate, Date toDate) {
        if (salesReport != null) {
            System.out.println("Sales Report from: " + salesReport.getFromDate());
            System.out.println("Sales Report to: " + salesReport.getToDate());
            System.out.println("Total Sales: " + salesReport.getTotalSales());
        }
    }

    public static void cliDemo(IPurchaseService purchaseService) {
        Scanner sc = new Scanner(System.in);
        String choice = "";
        while (!Objects.equals(choice, "q")) {
            System.out.println("\nPlease choose an option:");
            System.out.println("  1. Add new product");
            System.out.println("  2. Purchase product");
            System.out.println("  3. List all available products");
            System.out.println("  4. Remove product");
            System.out.println("  5. View overall purchase history");
            System.out.println("  6. View specific sales history");
            System.out.println("  q. Exit");

            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    try {
                        System.out.println("\nEnter name of new product: ");
                        String productName = sc.nextLine();

                        System.out.println("\nEnter product price: ");
                        int productPrice = sc.nextInt();
                        sc.nextLine();

                        System.out.println("\nEnter product ID: ");
                        int productId = sc.nextInt();
                        sc.nextLine();
                        purchaseService.addNewProduct(productId, productPrice, productName);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "2":
                    try {
                        displayAvailableProducts(purchaseService.getAvailableProducts());
                        System.out.println("\nInput productID that you want to purchase: ");
                        int productId = sc.nextInt();
                        sc.nextLine();
                        IProduct product = ((PurchaseService) purchaseService).fetchProductById(productId);
                        purchaseService.purchaseProduct(product);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid product ID.");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    displayAvailableProducts(purchaseService.getAvailableProducts());
                    break;

                case "4":
                    try {
                        displayAvailableProducts(purchaseService.getAvailableProducts());
                        System.out.println("Enter product ID that is to be removed");
                        int productId = sc.nextInt();
                        sc.nextLine();
                        purchaseService.removeProduct(productId);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid product ID.");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "5":
                    long currentTime = System.currentTimeMillis();
                    Date fromDate = new Date();
                    Date toDate = new Date(currentTime);
                    ISalesReport salesReport = purchaseService.getSalesReport(fromDate, toDate);
                    Utils.displaySoldProducts(salesReport, fromDate, toDate);
                    break;

                case "6":
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                        System.out.println("Enter the start date in dd-mm-yyyy format: ");
                        String startDate = sc.nextLine();
                        Date startDateParsed = dateFormat.parse(startDate);

                        System.out.println("Enter the end date in dd-mm-yyyy format: ");
                        String endDate = sc.nextLine();
                        Date endDateParsed = dateFormat.parse(endDate);

                        ISalesReport specificSalesReport = purchaseService.getSalesReport(startDateParsed, endDateParsed);
                        Utils.displaySalesReport(specificSalesReport, startDateParsed, endDateParsed);

                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "q":
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Not a valid choice!");
                    break;
            }
        }
    }
}
