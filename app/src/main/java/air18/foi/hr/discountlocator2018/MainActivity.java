package air18.foi.hr.discountlocator2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import air18.foi.hr.database.MainDatabase;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        MainDatabase.initDatabase(this);
    }
}
