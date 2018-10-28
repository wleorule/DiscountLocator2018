package air18.foi.hr.discountlocator.loaders;

import com.air.ws.core.DataLoadedListener;
import com.air.ws.core.DataLoader;
import com.air.ws.webservice.AirWebServiceCaller;
import com.air.ws.webservice.AirWebServiceHandler;

import java.util.ArrayList;
import java.util.List;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;

public class WsDataLoader extends DataLoader{
    private boolean storesArrived = false;
    private boolean discountsArrived = false;

    @Override
    public void loadData(DataLoadedListener dataLoadedListener) {
        super.loadData(dataLoadedListener);

        AirWebServiceCaller storesWs = new AirWebServiceCaller(storesHandler);
        AirWebServiceCaller discountsWs = new AirWebServiceCaller(discountsHandler);

        storesWs.getAll("getAll", Store.class);
        discountsWs.getAll("getAll", Discount.class);

    }

    //TODO: As an exercise, change the architecture so that you have only one AirWebServiceHandler

    AirWebServiceHandler storesHandler = new AirWebServiceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok, long timestamp) {
            if(ok){
                List<Store> stores = (List<Store>) result;
                for(Store store : stores){
                    store.save();
                }
                storesArrived = true;
                checkDataArrival();
            }
        }
    };

    AirWebServiceHandler discountsHandler = new AirWebServiceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok, long timestamp) {
            if(ok){
                List<Discount> discounts = (List<Discount>) result;
                for(Discount discount : discounts){
                    discount.save();
                }
                discountsArrived = true;
                checkDataArrival();
            }
        }
    };

    private void checkDataArrival(){
        if(storesArrived && discountsArrived){
            mDataLoadedListener.onDataLoaded((ArrayList<Store>) Store.getAll(), (ArrayList<Discount>) Discount.getAll());
        }
    }
}