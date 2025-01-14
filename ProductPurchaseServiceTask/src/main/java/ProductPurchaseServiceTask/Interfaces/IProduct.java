package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;

public interface IProduct {

    double getPrice();

    String getProductId();

    String getName();

    void setPurchaseDate(Date date);

    Date getPurchaseDate();
}
