package com.yenvth.bioinformaticsapp.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yenvth.bioinformaticsapp.R;
import com.yenvth.bioinformaticsapp.model.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHolder> {
    private Context mContext;
    private ArrayList<History>mListHistories;

    public HistoryAdapter(Context mContext, ArrayList<History> mListHistories) {
        this.mContext = mContext;
        this.mListHistories = mListHistories;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int pos) {
        History his = mListHistories.get(pos);
        holder.tvId.setText(his.getHis_id()+"");
    }

    @Override
    public int getItemCount() {
        return mListHistories.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
        }
    }
}
