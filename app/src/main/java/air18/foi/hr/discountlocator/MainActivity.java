package air18.foi.hr.discountlocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.air.ws.core.DataLoadedListener;
import com.air.ws.core.DataLoader;

import java.util.ArrayList;
import java.util.List;

import air18.foi.hr.database.MainDatabase;
import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;
import air18.foi.hr.discountlocator.adapters.ExpandableStoreItem;
import air18.foi.hr.discountlocator.adapters.StoreRecyclerAdapter;
import air18.foi.hr.discountlocator.loaders.DbDataLoader;
import air18.foi.hr.discountlocator.loaders.WsDataLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DataLoadedListener {

    StoreRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainDatabase.initDatabase(this);
        loadData();
    }


    public void loadData(){
        DataLoader dataLoader;
        if(Store.getAll().isEmpty()){
            System.out.println("Loading web data");
            dataLoader = new WsDataLoader();
        } else {
            System.out.println("Loading local data");
            dataLoader = new DbDataLoader();
        }
        dataLoader.loadData(this);
    }

    @Override
    public void onDataLoaded(ArrayList<Store> stores, ArrayList<Discount> discounts) {
        List<ExpandableStoreItem> storeItems = new ArrayList<>();

        if(stores != null){

            for(Store store : stores)
                storeItems.add(new ExpandableStoreItem(store));

            RecyclerView mRecycler = (RecyclerView) findViewById(R.id.main_recycler);
            if(mRecycler != null){
                mAdapter = new StoreRecyclerAdapter(this, storeItems);
                mRecycler.setAdapter(mAdapter);
                mRecycler.setLayoutManager(new LinearLayoutManager(this));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_app_preference:
                Intent intent = new Intent(this, AppPreferenceActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
