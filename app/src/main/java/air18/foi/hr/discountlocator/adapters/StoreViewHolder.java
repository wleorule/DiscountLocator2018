package air18.foi.hr.discountlocator.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.squareup.picasso.Picasso;

import air18.foi.hr.database.entities.Store;
import air18.foi.hr.discountlocator.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Izradio Miso 30.10.2018.
 */
public class StoreViewHolder extends ParentViewHolder {

    @BindView(R.id.store_name)
    TextView mStoreName;
    @BindView(R.id.store_desc)
    TextView mStoreDesc;
    @BindView(R.id.store_image)
    ImageView mStoreImage;

    View mStoreItemView;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);
        mStoreItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Store store){
        mStoreName.setText(store.getName());
        mStoreDesc.setText(store.getDescription());
        Picasso.with(mStoreItemView.getContext())
                .load(store.getImgUrl())
                .into(mStoreImage);
    }
}
