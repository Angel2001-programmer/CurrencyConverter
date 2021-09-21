package com.angelwitchell.currencyconverter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.angelwitchell.currencyconverter.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //variables
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    double USDGBP;
    double USDJPY;
    double USDCHF;
    double USDEUR;
    double USDHKD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<String> currencySymbols = new ArrayList<>();
        // items added to arrayList.
        currencySymbols.add("£");
        currencySymbols.add("¥");
        currencySymbols.add("Fr");
        currencySymbols.add("€");
        currencySymbols.add("hk$");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencySymbols);
        binding.spinner1.setAdapter(arrayAdapter);

        PlaceholderAPI placeholderAPI = RetrofitClient.getRetrofitInstance().create(PlaceholderAPI.class);
        Call<Model> call = placeholderAPI.getAllData();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                // Get data from web API

                Log.e(TAG, "onResponse: code : " + response.code());
                assert response.body() != null;
                USDGBP = response.body().getQuotes().getUSDGBP();
                USDJPY = response.body().getQuotes().getUSDJPY();
                USDCHF = response.body().getQuotes().getUSDCHF();
                USDEUR = response.body().getQuotes().getUSDEUR();
                USDHKD = response.body().getQuotes().getUSDHKD();

                Log.d(TAG, "onResponse: USDGBP " + USDGBP);
                Log.d(TAG, "onResponse: USDJPY " + USDJPY);
                Log.d(TAG, "onResponse: USDCHF " + USDCHF);
                Log.d(TAG, "onResponse: USDEUR " + USDEUR);
                Log.d(TAG, "onResponse: USDHKD " + USDHKD);
            }

            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        binding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String etValue = binding.etValue.getText().toString();
                switch (position) {
                    case 0:
                        if(!etValue.isEmpty()){

                        } else {
                            etValue = binding.etValue.getText().toString();
                            Log.d(TAG, "Convert: " + USDGBP);

                            double valueDouble = Double.parseDouble(etValue);
                            Log.d(TAG, "Convert: " + valueDouble);
                            double result = USDGBP * valueDouble;
                            Log.d(TAG, "DollorToPound: " + result);
                        }

//                        if(binding.etValue != null){
//                            binding.etValue.setText("0");
//                        } else {
//                            String etValue = binding.etValue.getText().toString();
//                            Log.d(TAG, "Convert: " + USDGBP);
//
//                            double valueDouble = Double.parseDouble(etValue);
//                            Log.d(TAG, "Convert: " + valueDouble);
//                            double result = USDGBP * valueDouble;
//                            Log.d(TAG, "DollorToPound: " + result);
//                        }
                        break;


                    case 1:
                        if(binding.etValue == null){
                            binding.etValue.setText("0");
                        } else {
                            etValue = binding.etValue.getText().toString();
                            Log.d(TAG, "Convert: " + USDJPY);

                            double valueDouble = Double.parseDouble(etValue);
                            Log.d(TAG, "Convert: " + valueDouble);
                            double result = USDJPY * valueDouble;
                            Log.d(TAG, "DollorToPound: " + result);
                        }
                        break;

                    case 2:
                        if(binding.etValue == null){
                            binding.etValue.setText("0");
                        } else {
                            etValue = binding.etValue.getText().toString();
                            Log.d(TAG, "Convert: " + USDCHF);

                            double valueDouble = Double.parseDouble(etValue);
                            Log.d(TAG, "Convert: " + valueDouble);
                            double result = USDCHF * valueDouble;
                            Log.d(TAG, "DollorToPound: " + result);
                        }
                        break;

                    case 3:
                        if(binding.etValue == null){
                            binding.etValue.setText("0");
                        } else {
                            etValue = binding.etValue.getText().toString();
                            Log.d(TAG, "Convert: " + USDEUR);

                            double valueDouble = Double.parseDouble(etValue);
                            Log.d(TAG, "Convert: " + valueDouble);
                            double result = USDEUR * valueDouble;
                            Log.d(TAG, "DollorToPound: " + result);
                        }
                        break;

                    case 4:

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    // do nothing
            }
        });
    }

    public void Convert(View view) {
            // Button function if it's pressed converts dollars to selected currency.
        EditText etValue = binding.etValue.getText().toString();

    }

    public void DollarToPound(EditText etValue){
        if(binding.etValue != null){
                        double valueDouble = Double.parseDouble(etValue);
                        Log.d(TAG, "Convert: " + valueDouble);
                        double result = USDHKD * valueDouble;
                        Log.d(TAG, "DollorToPound: " + result);
        }
    }
}