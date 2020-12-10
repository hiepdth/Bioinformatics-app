package com.yenvth.bioinformaticsapp.predict;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yenvth.bioinformaticsapp.HttpRequest;
import com.yenvth.bioinformaticsapp.R;
import com.yenvth.bioinformaticsapp.helper.DBHelper;
import com.yenvth.bioinformaticsapp.model.History;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PredictFragment extends Fragment implements View.OnClickListener {
    private EditText edProtein1, edProtein2;
    private ProgressBar progressBar;
    private TextView tvResult;
    private Button btnSubmit;

    private DBHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_predict, container, false);
        init(v);
        action();
        return v;
    }

    private void init(View v) {
        edProtein1 = v.findViewById(R.id.edProtein1);
        edProtein2 = v.findViewById(R.id.edProtein2);
        progressBar = v.findViewById(R.id.progressBar);
        tvResult = v.findViewById(R.id.tvResult);
        btnSubmit = v.findViewById(R.id.btnSubmit);

        helper = new DBHelper(getContext());
    }

    private void action() {
        edProtein1.setText("NP_061187.2");
        edProtein2.setText("NP_057694.2");

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                predict();
                break;
        }
    }

    private void predict() {
        String pro1 = edProtein1.getText().toString();
        String pro2 = edProtein2.getText().toString();

        //Todo: check conditions
        if (pro1.isEmpty() || pro2.isEmpty()) {
            Toast.makeText(getContext(), "please fill protein", Toast.LENGTH_SHORT).show();
            return;
        }

        new PredictProtein(pro1, pro2).execute();
    }

    class PredictProtein extends AsyncTask<Void, Void, String> {
        private String protein1;
        private String protein2;

        public PredictProtein(String protein1, String protein2) {
            this.protein1 = protein1;
            this.protein2 = protein2;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... voids) {
            String url = "http://10.0.2.2:5000/api";

            JSONObject object = new JSONObject();
            String res = "";

            try {
                publishProgress();
                object.put("protein1", protein1);
                object.put("protein2", protein2);

                res = HttpRequest.sendPost(url, object.toString());

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return res;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject object = new JSONObject(result);
                boolean interaction = object.getBoolean("iteractions");
                JSONObject object1 = object.getJSONObject("name");
                JSONArray array = object.getJSONArray("probability");
                JSONArray array1 = array.getJSONArray(0);
                String protein_name_1 = object1.get("protein1").toString();
                String protein_name_2 = object1.get("protein2").toString();

                double a = array1.getDouble(0);
                double b = array1.getDouble(1);
                int interact = interaction ? 1 : 0;
                double prob = Math.max(a, b);
                tvResult.setText("Interaction " + interaction + "\n" + "Probability " + prob);

                History history = new History(protein1, protein2, protein_name_1, protein_name_2, interact, prob);
                helper.insertHistory(history);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}