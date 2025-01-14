package ProductPurchaseServiceTask;

import ProductPurchaseServiceTask.Implementations.Product;
import ProductPurchaseServiceTask.Implementations.PurchaseService;
import ProductPurchaseServiceTask.Implementations.SoldProductSummary;
import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import ProductPurchaseServiceTask.Interfaces.IPurchaseService;

import java.util.Date;
import java.util.List;

public class AppTest {

    @Test
    public void serviceIsNull() {
        IPurchaseService service = null;
        Assert.assertNull(service);
    }

    // Write your own tests here, or in to another file.
    private PurchaseService purchaseService;

    // The project uses JUnit 4, but in JUnit5 this would be @BeforeEach
    @Before
    public void setUp() {
        purchaseService = new PurchaseService();
    }

    @Test
    public void testAddNewProduct_InvalidPrice() {
        Exception exception = null;
        try {
            purchaseService.addNewProduct(3, -5.00, "Invalid product price");
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for negative price", exception);
        assertEquals("ERROR ⚠: Price can't be set to negative number!", exception.getMessage());
    }

    @Test
    public void testAddNewProduct_EmptyName() {
        Exception exception = null;
        try {
            purchaseService.addNewProduct(2, 5.00, "");
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for empty name", exception);
        assertEquals("ERROR ⚠: Name can't be null!", exception.getMessage());
    }

    @Test
    public void testAddNewProduct_SpecialCharactersInName() {
        Exception exception = null;
        try {
            purchaseService.addNewProduct(7, 10.00, "In@valid#Name!@@@");
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for names with special characters", exception);
        assertEquals("ERROR ⚠: Name contains invalid characters!", exception.getMessage());
    }

    @Test
    public void testAddNewProduct_DuplicateId() {
        Exception exception = null;
        purchaseService.addNewProduct(5, 5.00, "SODA");
        try {
            purchaseService.addNewProduct(5, 8.00, "TICKET");
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for duplicate id", exception);
        assertEquals("ERROR ⚠: No duplicate productId's allowed!", exception.getMessage());
    }

    @Test
    public void testAddNewProduct_NegativePrice() {
        Exception exception = null;
        try {
            purchaseService.addNewProduct(6, -10.00, "Invalid Price Product");
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for negative price", exception);
        assertEquals("ERROR ⚠: Price can't be set to negative number!", exception.getMessage());
        assertFalse("Product with negative price shouldn't be added to available products",
                purchaseService.getAvailableProducts().containsKey(6));
    }

    @Test
    public void testAddNewProduct_StringPrice() {
        Exception exception = null;
        try {
            double price = Double.parseDouble("asd");
            purchaseService.addNewProduct(7, price, "Invalid String Price");
        } catch (NumberFormatException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for string price", exception);
        assertEquals("For input string: \"asd\"", exception.getMessage());
        assertFalse("Product with string price shouldn't be added to available products",
                purchaseService.getAvailableProducts().containsKey(7));
    }

    @Test
    public void testAddNewProduct_StringProductId() {
        Exception exception = null;
        try {
            int productId = Integer.parseInt("asd");
            purchaseService.addNewProduct(productId, 20.00, "Invalid String Product ID");
        } catch (NumberFormatException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for string product ID", exception);
        assertEquals("For input string: \"asd\"", exception.getMessage());
    }

    @Test
    public void testAddNewProduct_Valid() {
        purchaseService.addNewProduct(8, 15.00, "Valid Product");
        assertTrue("Product should be added to available products",
                purchaseService.getAvailableProducts().containsKey(8));
        assertEquals("Product price should match",
                15.00, purchaseService.getAvailableProducts().get(8).getPrice(), 0.01);
        assertEquals("Product name should match",
                "Valid Product", purchaseService.getAvailableProducts().get(8).getName());
    }


    // Tests for purchasing products
    @Test
    public void testPurchaseProduct_Valid() {
        IProduct product = new Product(4, 30.00, "Headphones");
        purchaseService.addNewProduct(4, 30.00, "Headphones");
        purchaseService.purchaseProduct(product);
        assertTrue("Purchased products should contain the headphones.",
                purchaseService.getPurchasedProducts().contains(product));
    }

    @Test
    public void testPurchaseProduct_NegativePrice() {
        Exception exception = null;
        try {
            IProduct product = new Product(4, -3.00, "Invalid product price");
            purchaseService.purchaseProduct(product);
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for negative price", exception);
        assertEquals("ERROR ⚠: Price can't be set to negative number!", exception.getMessage());
        assertFalse("Product with negative price shouldn't be allowed to be purchased", purchaseService.getAvailableProducts().containsKey(4));
    }

    @Test
    public void testPurchaseProduct_StringPrice() {
        Exception exception = null;
        try {
            double price = Double.parseDouble("asd");
            IProduct product = new Product(4, price, "Invalid product price");
            purchaseService.purchaseProduct(product);
        } catch (NumberFormatException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for string price", exception);
        assertEquals("For input string: \"asd\"", exception.getMessage());
    }

    @Test
    public void testPurchaseProduct_StringPID() {
        Exception exception = null;
        try {
            int price = Integer.parseInt("asd");
            IProduct product = new Product(price, 1, "Invalid product ID");
            purchaseService.purchaseProduct(product);
        } catch (NumberFormatException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for string ID", exception);
        assertEquals("For input string: \"asd\"", exception.getMessage());
    }

    @Test
    public void testPurchaseProduct_NotExist() {
        IProduct nonExistentProduct = new Product(999, 10.00, "Non-Existent Product");
        Exception exception = null;
        try {
            purchaseService.purchaseProduct(nonExistentProduct);
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for non-existent product", exception);
        assertEquals("ERROR ⚠: Product not found in available products.", exception.getMessage());
    }


    // Tests for sales reports
    @Test
    public void testSalesReport_NoPurchases() {
        Date fromDate = new Date(0);
        Date toDate = new Date(System.currentTimeMillis());

        ISalesReport salesReport = purchaseService.getSalesReport(fromDate, toDate);

        assertTrue("Sales report should have no products if none have been purchased.", salesReport.getSoldProducts().isEmpty());
    }

    @Test
    public void testSalesReport_Purchases() {
        purchaseService.addNewProduct(1, 5.00, "MOVIE TICKET");
        purchaseService.addNewProduct(2, 2.00, "SODA");
        purchaseService.purchaseProduct(new Product(1, 5.00, "MOVIE TICKET"));
        purchaseService.purchaseProduct(new Product(2, 2.00, "SODA"));
        Date fromDate = new Date(0);
        Date toDate = new Date(System.currentTimeMillis());
        ISalesReport salesReport = purchaseService.getSalesReport(fromDate, toDate);

        List<ISoldProductSummary> soldProducts = salesReport.getSoldProducts();
        Assert.assertEquals("There should be 2 sold products", 2, soldProducts.size());
    }

    @Test
    public void testSalesReport_HistoryZero() {
        long currentTime = System.currentTimeMillis();
        long oneDayAgo = currentTime - 24 * 60 * 60 * 1000;
        Date fromDate = new Date(0);
        Date toDate = new Date(oneDayAgo);

        purchaseService.addNewProduct(1, 5.00, "MOVIE TICKET");
        purchaseService.purchaseProduct(new Product(1, 5.00, "MOVIE TICKET"));

        ISalesReport salesReport = purchaseService.getSalesReport(fromDate, toDate);
        assertTrue("Sales report should have no products if none have been purchased.", salesReport.getSoldProducts().isEmpty());
    }


    // Tests for Sold Products summaries
    @Test
    public void testSoldProducts_ConstructorAndGetters() {
        String productName = "MOVIE TICKET";
        int quantity = 2;
        double totalPrice = 10.00;
        int productId = 1;

        Product product = new Product(productId, 5.00, productName);
        Date purchaseDate = new Date();
        product.setPurchaseDate(purchaseDate);

        SoldProductSummary summary = new SoldProductSummary(productName, quantity, totalPrice, productId, product.getPurchaseDate());

        assertEquals("Product name should be correct", summary.getProductName(), "MOVIE TICKET");
        assertEquals("Total price should be correct", totalPrice, summary.getTotalPrice(), 0);
        assertEquals("Product ID should be correct", String.valueOf(productId), String.valueOf(summary.getProductId()));
        assertEquals("Quantity sold should be correct", String.valueOf(quantity), String.valueOf(summary.getSoldAmount()));
    }

    @Test
    public void testSoldProducts_ZeroQuantity() {
        String productName = "SODA";
        int quantity = 0;
        double totalPrice = 0.00;
        int productId = 3;

        Product product = new Product(productId, 5.00, productName);
        Date purchaseDate = new Date();
        product.setPurchaseDate(purchaseDate);

        SoldProductSummary summary = new SoldProductSummary(productName, quantity, totalPrice, productId, product.getPurchaseDate());

        assertEquals("Product name should be correct", productName, summary.getProductName());
        assertEquals("Total price should be 0.00 for zero quantity", totalPrice, summary.getTotalPrice(), 0);
        assertEquals("Product ID should be correct", String.valueOf(productId), String.valueOf(summary.getProductId()));
        assertEquals("Sold amount should be 0 for no sales", String.valueOf(quantity), String.valueOf(summary.getSoldAmount()));
    }

    @Test
    public void testSoldProducts_NegativePrice() {
        String productName = "SODA";
        int quantity = 3;
        double totalPrice = -6.00;
        int productId = 2;

        Product product = new Product(productId, 5.00, productName);
        Date purchaseDate = new Date();
        product.setPurchaseDate(purchaseDate);

        try {
            new SoldProductSummary(productName, quantity, totalPrice, productId, product.getPurchaseDate());
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("ERROR ⚠: Price and quantity must be non-negative.", e.getMessage());
        }
    }
}
