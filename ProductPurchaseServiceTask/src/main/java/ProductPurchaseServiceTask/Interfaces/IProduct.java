package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;

public interface IProduct {

    double getPrice();

    int getProductId();

    String getName();

    void setPurchaseDate(Date date);

    Date getPurchaseDate();
}
