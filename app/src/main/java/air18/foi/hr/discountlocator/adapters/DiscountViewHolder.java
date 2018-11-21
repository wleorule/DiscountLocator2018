package air18.foi.hr.discountlocator.adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.air.ws.core.CurrentActivity;
import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;
import air18.foi.hr.discountlocator.R;
import air18.foi.hr.discountlocator.fragments.DiscountDetailsFragment;
import air18.foi.hr.discountlocator.fragments.DiscountListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

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

        DiscountDetailsFragment mDiscountDetailsFragment = new DiscountDetailsFragment();
        mDiscountDetailsFragment.setArguments(args);
        FragmentManager mFragmentManager = CurrentActivity.getActivity().getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_container, mDiscountDetailsFragment);
        mFragmentTransaction.addToBackStack("DiscountDetails");
        mFragmentTransaction.commit();
    }

    @OnLongClick
    public boolean discountLongSelected(){

        // AlertDialog import android.app.AlertDialog
        final AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
        final int parentPosition = getParentAdapterPosition();

        alertDialog.setTitle(itemView.getContext().getString(R.string.removal_question));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, itemView.getContext().getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Store parentStore = Store.getStoreById(mDiscount.getStoreId());
                // delete in database
                mDiscount.delete();
                // delete in list items
                mAdapter.getParentList().get(getParentAdapterPosition()).getChildList().remove(getChildAdapterPosition());
                // redraw list and remove this item
                mAdapter.notifyChildRemoved(getParentAdapterPosition(), getChildAdapterPosition());
                mAdapter.notifyDataSetChanged();

                if(mAdapter.getParentList().get(parentPosition).getChildList().size() == 0){
                    mAdapter.notifyParentRemoved(parentPosition);
                    parentStore.delete();
                    mAdapter.getParentList().remove(parentPosition);
                    mAdapter.notifyDataSetChanged();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, itemView.getContext().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

        return true;
    }
}
