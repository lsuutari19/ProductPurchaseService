package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;

import java.util.*;

public class PurchaseService implements IPurchaseService {
    private final List<IProduct> products = new ArrayList<>();
    private final List<IProduct> purchasedProducts = new ArrayList<>();

    public void addNewProduct(String productId, double price, String name) {
        if (price < 0) {
            throw new IllegalArgumentException("ERROR ⚠: Price can't be set to negative number!");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("ERROR ⚠: Name can't be null!");
        }
        if (products.stream().anyMatch(p -> p.getProductId().equals(productId))) {
            throw new IllegalArgumentException("ERROR ⚠: No duplicate productId's allowed!");
        }
        if (!name.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("ERROR ⚠: Name contains invalid characters!");
        }

        System.out.println("New product added. Name: " + name + " productId: " + productId + " price " + price);
        IProduct product = new Product(productId, price, name);
        products.add(product);
    }

    public List<IProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public IProduct fetchProductById(String productId) {
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ERROR ⚠: Product with ID " + productId + " not found in available products."));
    }

    @Override
    public void removeProduct(IProduct product) {
        if (products.remove(product)) {
            System.out.println("Product " + product.getName() + " with ID " + product.getProductId() + " removed.");
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public List<IProduct> getAvailableProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public void purchaseProduct(IProduct product) {
        long currentTime = System.currentTimeMillis();
        Date toDate = new Date(currentTime);

        boolean productExists = products.stream()
                .anyMatch(p -> p.getProductId().equals(product.getProductId()));

        if (!productExists) {
            throw new IllegalArgumentException("ERROR ⚠: Product not found in available products.");
        }

        if (product instanceof Product) {
            System.out.println("Setting purchase date for product: " + product.getName() + " Time: " + toDate);
            ((Product) product).setPurchaseDate(toDate);
        }

        purchasedProducts.add(product);
        System.out.println("Added product to purchased products: " + product.getName());
    }

    @Override
    public ISalesReport getSalesReport(Date fromDate, Date toDate) {
        return new SalesReport(fromDate, toDate, purchasedProducts);
    }
}
