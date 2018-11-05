package air18.foi.hr.discountlocator.adapters;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Izradio Miso 30.10.2018.
 */
public class ExpandableStoreItem extends Store implements Parent<Discount> {

    private List<Discount> discounts;

    public ExpandableStoreItem(Store store){
        super(store.getId(), store.getName(), store.getDescription(), store.getImgUrl(), store.getLongitude(), store.getLatitude());
        this.discounts = store.getDiscountList();
    }

    @Override
    public List<Discount> getChildList() {
        return discounts;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
