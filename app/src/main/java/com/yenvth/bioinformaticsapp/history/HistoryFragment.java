package com.yenvth.bioinformaticsapp.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yenvth.bioinformaticsapp.R;
import com.yenvth.bioinformaticsapp.helper.DBHelper;
import com.yenvth.bioinformaticsapp.model.History;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private ArrayList<History> mListHistories;
    private HistoryAdapter mAdapter;
    private RecyclerView mRecycler;

    private DBHelper helper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        init(v);
        action();
        return v;
    }

    private void init(View v) {
        mListHistories = new ArrayList<>();
        mRecycler = v.findViewById(R.id.mRecycler);
        helper = new DBHelper(getContext());
    }

    private void action() {
        mListHistories = helper.getHistories();

        mAdapter  = new HistoryAdapter(getContext(), mListHistories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, true);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setAdapter(mAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();

        mListHistories = helper.getHistories();
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

    }
}
