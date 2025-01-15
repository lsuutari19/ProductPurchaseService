package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.util.*;
import java.util.stream.Collectors;

public class SalesReport implements ISalesReport {
    private final Date fromDate;
    private final Date toDate;
    private final Map<IProduct, Date> purchasedProducts;

    public SalesReport(Date fromDate, Date toDate, Map<IProduct, Date> purchasedProducts) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.purchasedProducts = purchasedProducts;
    }

    @Override
    public Date getFromDate() {
        return this.fromDate;
    }

    @Override
    public Date getToDate() {
        return this.toDate;
    }

    @Override
    public double getTotalSales() {
        return purchasedProducts.entrySet().stream()
                .filter(entry -> isWithinDateRange(entry.getValue()))
                .mapToDouble(entry -> entry.getKey().getPrice())
                .sum();
    }

    @Override
    public List<ISoldProductSummary> getSoldProducts() {
        if (purchasedProducts.isEmpty()) {
            return new ArrayList<>();
        }

        Map<IProduct, Date> filteredProducts = purchasedProducts.entrySet().stream()
                .filter(entry -> isWithinDateRange(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (filteredProducts.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Integer, Long> productCounts = filteredProducts.keySet().stream()
                .collect(Collectors.groupingBy(IProduct::getProductId, Collectors.counting()));

        List<ISoldProductSummary> summaryList = productCounts.entrySet().stream()
                .map(entry -> {
                    int productId = entry.getKey();
                    long quantity = entry.getValue();

                    IProduct product = filteredProducts.keySet().stream()
                            .filter(p -> p.getProductId() == productId)
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Product not found with ID: " + productId));

                    double totalSales = product.getPrice() * quantity;
                    Date latestPurchaseDate = filteredProducts.entrySet().stream()
                            .filter(e -> e.getKey().getProductId() == productId)
                            .map(Map.Entry::getValue)
                            .max(Date::compareTo)
                            .orElse(null);

                    return new SoldProductSummary(
                            product.getName(),
                            (int) quantity,
                            totalSales,
                            product.getProductId(),
                            latestPurchaseDate
                    );
                })
                .collect(Collectors.toList());

        return summaryList;
    }

    private boolean isWithinDateRange(Date date) {
        return !date.before(fromDate) && !date.after(toDate);
    }
}
