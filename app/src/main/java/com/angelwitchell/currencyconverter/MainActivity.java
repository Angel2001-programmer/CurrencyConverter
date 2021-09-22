package com.angelwitchell.currencyconverter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.angelwitchell.currencyconverter.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //variables
    private static final String TAG = "MainActivity";
    double USDGBP;
    double USDJPY;
    double USDCHF;
    double USDEUR;
    double USDHKD;
    String symbol;
    private ActivityMainBinding binding;

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
        currencySymbols.add("CHf");
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
                switch (position) {
                    case 0:
                        Log.d(TAG, "onItemSelected: British Pounds");
                        symbol = "£";
                        break;

                    case 1:
                        Log.d(TAG, "onItemSelected: Japanese Yen");
                        symbol = "¥";
                        break;

                    case 2:
                        Log.d(TAG, "onItemSelected: Swiss Franc");
                        symbol = "CHf";
                        break;

                    case 3:
                        Log.d(TAG, "onItemSelected: Europe Euros");
                        symbol = "€";
                        break;

                    case 4:
                        Log.d(TAG, "onItemSelected: Hong Kong Dollars");
                        symbol = "hk$";
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
        String etValue = binding.etValue.getText().toString();

        if (etValue.isEmpty()) {
            binding.etValue.setError("Please enter a value into the TextField");
        } else {
            switch (symbol) {
                case "£":
                    DollarToPounds(etValue);
                    break;
                case "¥":
                    DollarToYen(etValue);
                    break;
                case "CHf":
                    DollarToCHf(etValue);
                    break;
                case "€":
                    DollarToEuros(etValue);
                    break;
                case "hk$":
                    DollarToHKD(etValue);
                    break;
            }
        }
    }

    public void DollarToPounds(String etValue) {

        if (binding.etValue != null) {
            double valueDouble = Double.parseDouble(etValue);
            Log.d(TAG, "Convert: " + valueDouble);
            double result = USDGBP * valueDouble;
            Log.d(TAG, "DollorToPound: " + result);

            NumberFormat priceFormat = NumberFormat.getCurrencyInstance();
            priceFormat.setCurrency(Currency.getInstance("USD"));
            String priceString = priceFormat.format(result);

            binding.tvResults.setText(String.valueOf(priceString));
        }
    }

    public void DollarToYen(String etValue) {

        if (binding.etValue != null) {
            double valueDouble = Double.parseDouble(etValue);
            Log.d(TAG, "Convert: " + valueDouble);
            double result = USDJPY * valueDouble;
            Log.d(TAG, "DollorToJPK: " + result);

            NumberFormat priceFormat = NumberFormat.getCurrencyInstance();
            priceFormat.setCurrency(Currency.getInstance("JPY"));
            String priceString = priceFormat.format(result);

            binding.tvResults.setText(String.valueOf(priceString));
        }
    }

    public void DollarToCHf(String etValue) {

        if (binding.etValue != null) {
            double valueDouble = Double.parseDouble(etValue);
            Log.d(TAG, "Convert: " + valueDouble);
            double result = USDCHF * valueDouble;
            Log.d(TAG, "DollorToCHf: " + result);
            NumberFormat priceFormat = NumberFormat.getCurrencyInstance();
            priceFormat.setCurrency(Currency.getInstance("SFr"));
            String priceString = priceFormat.format(result);

            binding.tvResults.setText(String.valueOf(priceString));
        }
    }

    public void DollarToEuros(String etValue) {

        if (binding.etValue != null) {
            double valueDouble = Double.parseDouble(etValue);
            Log.d(TAG, "Convert: " + valueDouble);
            double result = USDEUR * valueDouble;
            Log.d(TAG, "DollorToEURO: " + result);

            NumberFormat priceFormat = NumberFormat.getCurrencyInstance();
            priceFormat.setCurrency(Currency.getInstance("EUR"));
            String priceString = priceFormat.format(result);

            binding.tvResults.setText(String.valueOf(priceString));
        }
    }

    public void DollarToHKD(String etValue) {

        if (binding.etValue != null) {
            double valueDouble = Double.parseDouble(etValue);
            Log.d(TAG, "Convert: " + valueDouble);
            double result = USDHKD * valueDouble;
            Log.d(TAG, "DollorToHKD: " + result);

            NumberFormat priceFormat = NumberFormat.getCurrencyInstance();
            priceFormat.setCurrency(Currency.getInstance("HKD"));
            String priceString = priceFormat.format(result);

            binding.tvResults.setText(String.valueOf(priceString));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textview", binding.tvResults.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        binding.tvResults.setText(savedInstanceState.getString("textview"));
    }
}