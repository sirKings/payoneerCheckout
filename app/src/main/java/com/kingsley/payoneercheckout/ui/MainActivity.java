package com.kingsley.payoneercheckout.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kingsley.payoneercheckout.R;
import com.kingsley.payoneercheckout.ui.adapter.ResultAdapter;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ResultAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setUpUi();
        setUpObservers();

    }

    private void setUpUi(){
        progressBar = findViewById(R.id.progressBar);
        adapter = new ResultAdapter();
        RecyclerView rv = findViewById(R.id.resultRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        findViewById(R.id.backBtn).setOnClickListener( view -> finish());
    }

    private void setUpObservers(){

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        model.getLoadingStatus().observe(this, status -> {
            if(status){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        });
        model.getCheckoutOptions().observe(this, results -> {
            adapter.setListDataItems(results);
            adapter.notifyDataSetChanged();
        });

        model.getLoadingError().observe(this, error -> Toast.makeText(this, error, Toast.LENGTH_LONG).show());
    }

}