package com.example.apara.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleView;
        private TextView mDateTextView;
        private Crime mCrime;
        private Button mPoliceButton;
        public CrimeHolder(View inflater, ViewGroup parent) {
            super(inflater);
            itemView.setOnClickListener(this);
            mTitleView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
        }
        public void bind(Crime crime){
            mCrime = crime;
            mTitleView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
            private List<Crime> mCrimes;

            public CrimeAdapter(List<Crime> crimes){
                mCrimes = crimes;
            }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
            View layoutInflater;
                if(getItemViewType(position) % 2 == 0) {
                    layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crimes_police, parent, false);
                }else{
                    layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crime, parent, false);
                }
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int position) {
            Crime crime = mCrimes.get(position);
            crimeHolder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
