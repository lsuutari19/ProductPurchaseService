package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesReport implements ISalesReport {
    private final Date fromDate;
    private final Date toDate;
    private final List<IProduct> soldProducts;

    public SalesReport(Date fromDate, Date toDate, List<IProduct> soldProducts) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.soldProducts = soldProducts;
    }

    public Date getFromDate() {
        return this.fromDate;
    }

    public Date getToDate() {
        return this.toDate;
    }

    public double getTotalSales() {
        return soldProducts.stream().mapToDouble(IProduct::getPrice).sum();
    }

    public List<ISoldProductSummary> getSoldProducts() {
        if (soldProducts.isEmpty()) {
            return new ArrayList<>();
        }

        Map<String, Long> productCounts = soldProducts.stream()
                .collect(Collectors.groupingBy(IProduct::getProductId, Collectors.counting()));

        List<ISoldProductSummary> summaryList = productCounts.entrySet().stream()
                .map(entry -> {
                    String productId = entry.getKey();
                    long quantity = entry.getValue();

                    IProduct product = soldProducts.stream()
                            .filter(p -> p.getProductId().equals(productId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Product not found with ID: " + productId));

                    return new SoldProductSummary(
                            product.getName(),
                            (int) quantity,
                            product.getPrice() * quantity,
                            product.getProductId()
                    );
                })
                .collect(Collectors.toList());

        return summaryList;
    }
}
