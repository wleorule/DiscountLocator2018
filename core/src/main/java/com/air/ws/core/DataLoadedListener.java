package com.air.ws.core;

import java.util.ArrayList;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;

public interface DataLoadedListener {
    void onDataLoaded(ArrayList<Store> stores, ArrayList<Discount> discounts);
}
