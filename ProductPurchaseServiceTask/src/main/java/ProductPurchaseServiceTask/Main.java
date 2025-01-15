package ProductPurchaseServiceTask;

import ProductPurchaseServiceTask.Implementations.PurchaseService;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Utils.Utils;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nStarting the application!");
        IPurchaseService purchaseService = new PurchaseService();

        // Start the demo CLI app
        Utils.cliDemo(purchaseService);
    }
}
