package air18.foi.hr.discountlocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.air.ws.core.DataLoadedListener;

import java.util.ArrayList;

import air18.foi.hr.database.MainDatabase;
import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DataLoadedListener {

    @BindView(R.id.discount_list)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        MainDatabase.initDatabase(this);
    }

    @OnClick(R.id.test_button)
    public void buttonClicked(View view){
        final String[] listItems = MainDatabase.getDiscounts();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onDataLoaded(ArrayList<Store> stores, ArrayList<Discount> discounts) {
        System.out.println("Data is here... ");
        String[] listItems = new String[discounts.size()];

        for (int i = 0; i < discounts.size(); i++) {
            listItems[i] = discounts.get(i).getName();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }
}
