package air18.foi.hr.discountlocator;

import com.air.ws.core.DataLoadedListener;
import com.air.ws.core.DataLoader;
import com.air.ws.core.NavigationItem;

import java.util.ArrayList;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;
import air18.foi.hr.discountlocator.loaders.DbDataLoader;
import air18.foi.hr.discountlocator.loaders.WsDataLoader;

public class DataManager {
    //singleton
    private static DataManager instance;

    private DataManager()
    {

    }

    public static DataManager getInstance()
    {
        if (instance == null)
            instance = new DataManager();

        return instance;
    }

    public void sendData(final NavigationItem module){
        DataLoader dataLoader;
        if(Store.getAll().isEmpty()){
            System.out.println("Loading web data");
            dataLoader = new WsDataLoader();
        } else {
            System.out.println("Loading local data");
            dataLoader = new DbDataLoader();
        }
        dataLoader.loadData(new DataLoadedListener() {
            @Override
            public void onDataLoaded(ArrayList<Store> stores, ArrayList<Discount> discounts) {
                module.setData(stores, discounts);
            }
        });
    }
}