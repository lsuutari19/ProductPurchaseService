package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;

public interface ISoldProductSummary {
    int getSoldAmount();

    double getTotalPrice();

    Date getPurchaseDate();
}
