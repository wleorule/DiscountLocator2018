package air18.foi.hr.discountlocator.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.air.ws.core.DataLoadedListener;
import com.air.ws.core.DataLoader;
import com.air.ws.core.NavigationItem;

import java.util.ArrayList;
import java.util.List;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;
import air18.foi.hr.discountlocator.R;
import air18.foi.hr.discountlocator.adapters.ExpandableStoreItem;
import air18.foi.hr.discountlocator.adapters.StoreRecyclerAdapter;
import air18.foi.hr.discountlocator.loaders.DbDataLoader;
import air18.foi.hr.discountlocator.loaders.WsDataLoader;

public class DiscountListFragment extends Fragment implements DataLoadedListener, NavigationItem {
    StoreRecyclerAdapter mAdapter;
    private List<Store> stores;
    private List<Discount> discounts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discount_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //loadData();
    }

    @Override
    public void onDataLoaded(ArrayList<Store> stores, ArrayList<Discount> discounts) {
        List<ExpandableStoreItem> storeItems = new ArrayList<>();

        if(stores != null){

            for(Store store : stores)
                storeItems.add(new ExpandableStoreItem(store));

            RecyclerView mRecycler = (RecyclerView) getActivity().findViewById(R.id.main_recycler);
            if(mRecycler != null){
                mAdapter = new StoreRecyclerAdapter(getActivity(), storeItems);
                mRecycler.setAdapter(mAdapter);
                mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        }
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.menu_list);
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getDrawable(android.R.drawable.ic_menu_agenda);
    }

    @Override
    public void setData(List<Store> stores, List<Discount> discounts) {

        this.stores = stores;
        this.discounts = discounts;
    }
}
