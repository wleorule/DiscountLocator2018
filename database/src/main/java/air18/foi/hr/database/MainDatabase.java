package air18.foi.hr.database;

import android.content.Context;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

@Database(name = MainDatabase.NAME, version = MainDatabase.VERSION)
public class MainDatabase {
    public static final String NAME = "main";
    public static final int VERSION = 1;

    public static void initDatabase(Context context)
    {
        FlowManager.init(new FlowConfig.Builder(context).build());
    }
}
