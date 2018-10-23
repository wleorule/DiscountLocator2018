package air18.foi.hr.database;

import android.content.Context;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import air18.foi.hr.database.data.MockData;
import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Discount_Table;
import air18.foi.hr.database.entities.Store;

@Database(name = MainDatabase.NAME, version = MainDatabase.VERSION)
public class MainDatabase {
    public static final String NAME = "main";
    public static final int VERSION = 1;

    public static void initDatabase(Context context)
    {
        FlowManager.init(new FlowConfig.Builder(context).build());
    }

    public static void mockAndLogData()
    {
        if(SQLite.select().from(Store.class).queryList().isEmpty()){
            MockData.writeAll();
        }

        List<Discount> discounts = SQLite.select().from(Discount.class).where(Discount_Table.discount.greaterThan(5)).queryList();

        for(Discount discount : discounts){
            System.out.println("Discount: " + discount.getName());
        }
    }
}
