package tsp.lucas.nba01.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import tsp.lucas.nba01.Fragments.Gameview_fragment;
import tsp.lucas.nba01.R;
import tsp.lucas.nba01.SectionsStatePagerAdapter;
import tsp.lucas.nba01.Fragments.Soundboard_fragment;
import tsp.lucas.nba01.Fragments.Statistics_fragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsStatePagerAdapter mSectionsStatePageAdapter;
    private ViewPager mViewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Gameview:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_Statistics:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_Soundboard:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                MenuItem item = navigation.getMenu().getItem(i);
                navigation.setSelectedItemId(item.getItemId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        Intent incomingIntent = getIntent();


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Gameview_fragment(), "Gameview");
        adapter.addFragment(new Statistics_fragment(), "Statistics");
        adapter.addFragment(new Soundboard_fragment(), "Soundboard");
        viewPager.setAdapter(adapter);
    }



}
