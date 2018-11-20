package air18.foi.hr.discountlocator.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.discountlocator.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscountDetailsFragment extends Fragment {
    @BindView(R.id.discount_details_name)
    TextView txtName;

    @BindView(R.id.discount_details_description)
    TextView txtDescription;

    @BindView(R.id.discount_details_start)
    TextView txtStartDate;

    @BindView(R.id.discount_details_end)
    TextView txtEndDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discount_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, getView());

        showDiscountDetails();
    }

    private void showDiscountDetails() {
        Bundle data = this.getArguments();
        int discountId = data.getInt("id", -1);

        if(discountId != -1){
            Discount discount = Discount.getDiscountById(discountId);

            txtName.setText(discount.getName());
            txtDescription.setText(discount.getDescription());

            // import java.text.SimpleDateFormat;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            txtStartDate.setText(sdf.format(discount.getStartDate()));
            txtEndDate.setText(" -- " + sdf.format(discount.getEndDate()));
        }
    }
}
