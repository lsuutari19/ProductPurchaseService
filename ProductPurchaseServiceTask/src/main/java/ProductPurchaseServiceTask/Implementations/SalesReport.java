package ProductPurchaseServiceTask.Implementations;

import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

import java.util.*;
import java.util.stream.Collectors;

public class SalesReport implements ISalesReport {
    private Date fromDate;
    private Date toDate;
    private List<IProduct> soldProducts;

    public SalesReport(Date fromDate, Date toDate, List<IProduct> soldProducts) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.soldProducts = soldProducts;
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
        return soldProducts.stream().mapToDouble(IProduct::getPrice).sum();
    }


    @Override
    public List<ISoldProductSummary> getSoldProducts() {
        if (soldProducts.isEmpty()) {
            return new ArrayList<>();
        }

        List<IProduct> filteredProducts = soldProducts.stream()
                .filter(product -> !product.getPurchaseDate().before(fromDate) && !product.getPurchaseDate().after(toDate))
                .collect(Collectors.toList());

        if (filteredProducts.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Integer, Long> productCounts = filteredProducts.stream()
                .collect(Collectors.groupingBy(IProduct::getProductId, Collectors.counting()));

        List<ISoldProductSummary> summaryList = productCounts.entrySet().stream()
                .map(entry -> {
                    int productId = entry.getKey();
                    long quantity = entry.getValue();

                    IProduct product = filteredProducts.stream()
                            .filter(p -> p.getProductId() == productId)
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Product not found with ID: " + productId));

                    return new SoldProductSummary(
                            product.getName(),
                            (int) quantity,
                            product.getPrice() * quantity,
                            product.getProductId(),
                            product.getPurchaseDate()
                    );
                })
                .collect(Collectors.toList());

        return summaryList;
    }
}
