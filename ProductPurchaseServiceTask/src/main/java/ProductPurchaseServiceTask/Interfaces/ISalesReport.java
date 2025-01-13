package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;
import java.util.List;

public interface ISalesReport {

    /**
     * Start date of this report. This report should not contain any records before this date. 
     * @return Start date of this report
     */
    Date getFromDate();
    /**
     * End date of this report. This report should not contain any records after this date.
     * @return End date of this report
     */
    Date getToDate();

    double getTotalSales();

    /**
     * Returns summary of sold products during this reports period
     * @return Collection of Product summaries sold in this sales report
     */
    List<ISoldProductSummary> getSoldProducts();

}
