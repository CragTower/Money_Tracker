package com.cragtowercreations.moneyhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Declare Variables
    TextView availableFunds;
    TextView savingsAccount;
    Button addTransactionButton;
    AppDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate variables
        availableFunds = (TextView) findViewById(R.id.availableFunds);
        savingsAccount = (TextView) findViewById(R.id.savingsAmount);
        addTransactionButton = (Button) findViewById(R.id.addTransactionButton);
        DB = new AppDatabase(this);

        // OnClick listener assignment
        addTransactionButton.setOnClickListener(addTransactionButtonListener);

        // On page creation call database to calculate Funds Available
        calculateFundsAvailable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateFundsAvailable();
    }

    private View.OnClickListener addTransactionButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), AddTransactionActivity.class);
            startActivity(intent);
        }
    };

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