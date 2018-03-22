package sk.rain.men.abc.tracking.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import sk.rain.men.abc.tracking.AbcMasterDataActivity;
import sk.rain.men.abc.tracking.AbcMasterDataTabActivity;
import sk.rain.men.abc.tracking.ChildrenActivity;
import sk.rain.men.abc.tracking.MessageKey;
import sk.rain.men.abc.tracking.R;
import sk.rain.men.abc.tracking.SimpleAbcDataFragmentPagerAdapter;
import sk.rain.men.abc.tracking.model.AbcType;

public class AbcChildDataTabActivity extends FragmentActivity {

    private SimpleAbcFormFragmentPagerAdapter pageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_form_data_tab);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.abc_form_viewpager);
        viewPager.setOffscreenPageLimit(3);

        Long childId = getIntent().getLongExtra(MessageKey.CHILD_ID_KEY, -1);

        // Create an adapter that knows which fragment should be shown on each page
        pageAdapter = new SimpleAbcFormFragmentPagerAdapter(getSupportFragmentManager(), this, childId);

        // Set the adapter onto the view pager
        viewPager.setAdapter(pageAdapter);


        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.abc_form_tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton updateData = findViewById(R.id.update_abc_form_data_button);
        if (updateData != null) {
            updateData.setVisibility(View.VISIBLE);

            updateData.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AbcChildDataTabActivity.this, AbcMasterDataTabActivity.class);
                    int pageIndex = viewPager.getCurrentItem();
                    intent.putExtra(MessageKey.ABC_DATA_PAGE_INDEX, pageIndex);
                    intent.putExtra(MessageKey.ABC_DATA_SELECTED_LIST, 0);
                    startActivity(intent);
                }
            });
        }
    }

    public void createAntecedent(View view) {
        Intent intent = new Intent(this, AbcMasterDataActivity.class);
        intent.putExtra(MessageKey.CHILD_ID_KEY, AbcType.Antecedent);
        startActivity(intent);
    }

    public void createBehavior(View view) {
        Intent intent = new Intent(this, AbcMasterDataActivity.class);
        intent.putExtra(MessageKey.CHILD_ID_KEY, AbcType.Behavior);
        startActivity(intent);
    }

    public void createConsequence(View view) {
        Intent intent = new Intent(this, AbcMasterDataActivity.class);
        intent.putExtra(MessageKey.CHILD_ID_KEY, AbcType.Consequence);
        startActivity(intent);
    }
}
