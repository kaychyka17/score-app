package si.karin.scoreapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private EditText etAway;
    private EditText etHome;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        etHome = (EditText) findViewById(R.id.et_home);
        etAway = (EditText) findViewById(R.id.et_away);
        Button btnSetResults = (Button) findViewById(R.id.btn_set_result);
        btnSetResults.setOnClickListener(this);

    }

    MatchPitchCallback callback;

    public void setMatchPitchCallback(MatchPitchCallback callback) {
        this.callback = callback;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_result:

                int awayResult = getIntFromEditText(etAway);
                int homeResult = getIntFromEditText(etHome);
                callback.setResult(homeResult, awayResult);

                break;
        }
    }

    private int getIntFromEditText(EditText editText) {
        String score = editText.getText().toString();
        if (!score.equals("")) {
            return Integer.parseInt(score);
        } else {
            return 0;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PitchFragment (defined as a static inner class below).
            if (position == 0) {
                return FootballPitchFragment.newInstance();
            } else if (position == 1) {
                return BasketballPitchFragment.newInstance();
            } else if (position == 2) {
                return TennisPitchFragment.newInstance();
            }
            return null;
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.e("POSITION", "pos "+position);
            return super.instantiateItem(container, position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.football);
                case 1:
                    return getString(R.string.basketball);
                case 2:
                    return getString(R.string.tennis);
            }
            return null;
        }
    }
}
