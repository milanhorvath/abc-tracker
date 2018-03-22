package sk.rain.men.abc.tracking;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sk.rain.men.abc.tracking.model.AbcMasterData;
import sk.rain.men.abc.tracking.model.AbcType;

public class AbcMasterDataTabActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_master_data_tab);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.abc_data_viewpager);
        viewPager.setOffscreenPageLimit(3);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleAbcDataFragmentPagerAdapter adapter = new SimpleAbcDataFragmentPagerAdapter(getSupportFragmentManager(), this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.abc_data_tabs);
        AbcType type = (AbcType) getIntent().getSerializableExtra(MessageKey.ABC_MD_TYPE_KEY);
        if (type != null) {
            switch (type) {
                case Antecedent:
                    viewPager.setCurrentItem(0);
                    break;
                case Behavior:
                    viewPager.setCurrentItem(1);
                    break;
                case Consequence:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
        tabLayout.setupWithViewPager(viewPager);
    }

    public void createAntecedent(View view) {
        Intent intent = new Intent(this, AbcMasterDataActivity.class);
        intent.putExtra(MessageKey.ABC_MD_TYPE_KEY, AbcType.Antecedent);
        startActivity(intent);
    }

    public void createBehavior(View view) {
        Intent intent = new Intent(this, AbcMasterDataActivity.class);
        intent.putExtra(MessageKey.ABC_MD_TYPE_KEY, AbcType.Behavior);
        startActivity(intent);
    }

    public void createConsequence(View view) {
        Intent intent = new Intent(this, AbcMasterDataActivity.class);
        intent.putExtra(MessageKey.ABC_MD_TYPE_KEY, AbcType.Consequence);
        startActivity(intent);
    }

}
