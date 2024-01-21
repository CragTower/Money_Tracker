package com.cragtowercreations.moneyhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Declare Variables
    TextView availableFunds, savingsAccount;
    AppDatabase DB;

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

            case R.id.main_activity:
                Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMain);
                return true;

            case R.id.transaction_add_activity:
                Intent intentAddTransaction = new Intent(getApplicationContext(), AddTransactionActivity.class);
                startActivity(intentAddTransaction);
                return true;

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
        setContentView(R.layout.activity_main);

        // Initiate variables
        availableFunds = (TextView) findViewById(R.id.availableFunds);
        savingsAccount = (TextView) findViewById(R.id.savingsAmount);
        DB = new AppDatabase(this);

        // On page creation call database to calculate Funds Available
        calculateFundsAvailable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateFundsAvailable();
    }

    private void calculateFundsAvailable () {
        List<Double> incomeList = DB.getAllIncomes();
        List<Double> expenseList = DB.getAllExpenses();
        Double incomes = 0.0;
        Double expenses = 0.0;

        for (int i = 0; i < incomeList.size(); i++) {
            incomes += incomeList.get(i);
        }

        for (int i = 0; i < expenseList.size(); i++) {
            expenses += expenseList.get(i);
        }

        Double funds = incomes - expenses;
        availableFunds.setText(String.format("%.2f", funds));
    }
}