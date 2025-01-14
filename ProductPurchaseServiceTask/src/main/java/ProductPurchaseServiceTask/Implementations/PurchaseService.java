package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;

import java.util.*;

public class PurchaseService implements IPurchaseService {
    private final Map<Integer, IProduct> products = new HashMap<>();
    private final List<IProduct> purchasedProducts = new ArrayList<>();

    @Override
    public void addNewProduct(int productId, double price, String name) {
        if (price < 0) {
            throw new IllegalArgumentException("ERROR ⚠: Price can't be set to negative number!");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("ERROR ⚠: Name can't be null!");
        }
        if (productId < 0) {
            throw new IllegalArgumentException("ERROR ⚠: ProductId can't be set to negative number!");
        }
        if (products.containsKey(productId)) {
            throw new IllegalArgumentException("ERROR ⚠: No duplicate productId's allowed!");
        }
        System.out.println("New product added. Name: " + name + " productId: " + productId + " price " + price);
        IProduct product = new Product(productId, price, name);
        products.put(productId, product);
    }

    public List<IProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public IProduct fetchProductById(int productId) {
        IProduct product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("ERROR ⚠: Product with ID " + productId + " not found in available products.");
        }
        return product;
    }

    @Override
    public void removeProduct(int productId) {
        IProduct removedProduct = products.remove(productId);
        if (removedProduct != null) {
            System.out.println("Product with ID " + productId + " removed.");
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    @Override
    public Map<Integer, IProduct> getAvailableProducts() {
        return products;
    }

    @Override
    public void purchaseProduct(IProduct product) {
        long currentTime = System.currentTimeMillis();
        Date toDate = new Date(currentTime);
        if (!products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("ERROR ⚠: Product not found in available products.");
        }
        if (product instanceof Product) {
            System.out.println("Setting purchase date for product: " + product.getName() + " Time: " + toDate);
            product.setPurchaseDate(toDate);
        }
        purchasedProducts.add(product);
        System.out.println("Added product to purchasedProducts: " + product);
    }

    @Override
    public ISalesReport getSalesReport(Date fromDate, Date toDate) {
        return new SalesReport(fromDate, toDate, purchasedProducts);
    }
}
