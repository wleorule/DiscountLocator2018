package air18.foi.hr.database.entities;

import java.util.Date;

public class Discount {
    int id;
    String name;
    String description;
    int storeId;
    Date startDate; // java.util.Date
    Date endDate;
    int discount;
}
