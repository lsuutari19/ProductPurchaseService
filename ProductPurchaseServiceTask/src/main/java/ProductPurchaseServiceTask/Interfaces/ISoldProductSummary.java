package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;

public interface ISoldProductSummary {

    String getProductName();

    int getSoldAmount();

    double getTotalPrice();

    int getProductId();

    Date getPurchaseDate();
}
