package com.example.apara.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity implements CrimeFragment.Callbacks {
    public static final String EXTRA_CRIME_ID = "*.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mLastButton;
    private Button mFirstButton;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        mViewPager = findViewById(R.id.crime_view_pager);
        mLastButton = findViewById(R.id.last_button);
        mFirstButton = findViewById(R.id.first_button);

        mCrimes = CrimeLab.get(this).getCrimes();
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                if (mViewPager.getCurrentItem() != mCrimes.size() - 1) {
                    mLastButton.setVisibility(View.VISIBLE);
                } else {
                    mLastButton.setVisibility(View.GONE);
                }
                if (mViewPager.getCurrentItem() != 0) {
                    mFirstButton.setVisibility(View.VISIBLE);
                } else {
                    mFirstButton.setVisibility(View.GONE);
                }
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        int crimeSize = mCrimes.size();
        for (int i = 0; i < crimeSize; i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCrimes.size() - 1);
                mLastButton.setVisibility(View.GONE);
            }
        });
        mFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                mFirstButton.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
