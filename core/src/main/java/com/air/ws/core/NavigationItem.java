package com.air.ws.core;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import java.util.List;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;

public interface NavigationItem {
    public Fragment getFragment();
    public String getName(Context context);
    public Drawable getIcon(Context context);
    public void setData(List<Store> stores, List<Discount> discounts);
}
