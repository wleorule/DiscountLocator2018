package air18.foi.hr.discountlocator.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.discountlocator.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Izradio Miso 30.10.2018.
 */
public class DiscountViewHolder extends ChildViewHolder {
    @BindView(R.id.discount_name)
    TextView mDiscountName;
    @BindView(R.id.discount_desc)
    TextView mDiscountDesc;
    @BindView(R.id.discount_value)
    TextView mDiscountValue;

    Discount mDiscount;
    View mDiscountItemView;
    StoreRecyclerAdapter mAdapter;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public DiscountViewHolder(@NonNull View itemView, StoreRecyclerAdapter adapter) {
        super(itemView);
        mDiscountItemView = itemView;
        mAdapter = adapter;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Discount discount){
        mDiscount = discount;
        mDiscountName.setText(discount.getName());
        mDiscountDesc.setText(discount.getDescription());
        mDiscountValue.setText(discount.getDiscount() + "%");
    }

    @OnClick
    public void discountSelected(){
        Bundle args = new Bundle();
        args.putInt("id", mDiscount.getId());
    }
}
