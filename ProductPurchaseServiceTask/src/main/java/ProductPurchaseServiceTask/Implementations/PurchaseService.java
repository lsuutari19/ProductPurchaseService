package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;

import java.util.*;

public class PurchaseService implements IPurchaseService {
    private final Map<Integer, IProduct> products = new HashMap<>();
    private final Map<IProduct, Date> purchasedProducts = new HashMap<>();

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
        if (!name.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("ERROR ⚠: Name contains invalid characters!");
        }

        System.out.println("New product added. Name: " + name + " productId: " + productId + " price " + price);
        IProduct product = new Product(productId, price, name);
        products.put(productId, product);
    }

    public Map<IProduct, Date> getPurchasedProducts() {
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
        if (!products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("ERROR ⚠: Product not found in available products.");
        }

        Date purchaseDate = new Date();
        purchasedProducts.put(product, purchaseDate);

        System.out.println("Product purchased: " + product.getName() + " at " + purchaseDate);
    }

    @Override
    public ISalesReport getSalesReport(Date fromDate, Date toDate) {
        Map<IProduct, Date> filteredProductsMap = new HashMap<>();

        for (Map.Entry<IProduct, Date> entry : purchasedProducts.entrySet()) {
            Date purchaseDate = entry.getValue();
            if (!purchaseDate.before(fromDate) && !purchaseDate.after(toDate)) {
                filteredProductsMap.put(entry.getKey(), entry.getValue());
            }
        }

        return new SalesReport(fromDate, toDate, filteredProductsMap);
    }
}
