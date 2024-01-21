package com.cragtowercreations.moneyhandler;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<TransactionItems> transactionItems;
    AppDatabase DB;
    MyAdapter adapter;

    // Creates hamburger menu with pages in application
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    // Switch pages when corresponding item selected in menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            // Call main page activity
            case R.id.main_activity:
                Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMain);
                return true;

            // Call add transaction page activity
            case R.id.transaction_add_activity:
                Intent intentAddTransaction = new Intent(getApplicationContext(), AddTransactionActivity.class);
                startActivity(intentAddTransaction);
                return true;

            // Call view transaction page activity
            case R.id.transaction_view_activity:
                Intent intentViewTransaction = new Intent(getApplicationContext(), TransactionViewActivity.class);
                startActivity(intentViewTransaction);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_view_activity);

        // Initialize variables
        DB = new AppDatabase(this);
        transactionItems = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this, transactionItems);

        // Set adapter and layout manager to recycler view
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayData();
    }

    // Logic to collect and bind data to views for recycler view page
    private void displayData()  {
        Cursor cursor = DB.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No entry exists", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()) {
                transactionItems.add(new TransactionItems(Integer.parseInt(cursor.getString(0)),
                                                            cursor.getString(1),
                                                            cursor.getString(2),
                                                            cursor.getString(3)));
            }
        }
    }

}
