package air18.foi.hr.discountlocator;

import com.air.ws.core.NavigationItem;

import java.util.ArrayList;
import java.util.List;

import air18.foi.hr.discountlocator.fragments.DiscountListFragment;
import air18.foi.hr.map.MapFragment;

public class NavigationManager {
    //TODO - manage drawer
    //TODO - manage data

    //singleton
    private static NavigationManager instance;

    //manage modules
    private List<NavigationItem> navigationItems;

    private NavigationManager()
    {
        navigationItems = new ArrayList<>();
        navigationItems.add(new DiscountListFragment());
        navigationItems.add(new MapFragment());
    }

    public static NavigationManager getInstance()
    {
        if (instance == null)
            instance = new NavigationManager();

        return instance;
    }
}