package com.cragtowercreations.moneyhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddTransactionActivity extends AppCompatActivity {

    // Declaring variables
    TextView transactionDescription;
    EditText incomeEditText, expenseEditText;
    Button addIncomeButton, addExpenseButton;
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
        setContentView(R.layout.transaction_page);

        // Initializing variables
        transactionDescription = (TextView) findViewById(R.id.transactionDescription);
        incomeEditText = (EditText) findViewById(R.id.incomeEditText);
        expenseEditText = (EditText) findViewById(R.id.expenseEditText);
        addIncomeButton = (Button) findViewById(R.id.addIncomeButton);
        addExpenseButton = (Button) findViewById(R.id.addExpenseButton);
        DB = new AppDatabase(this);

        // Initialize OnClick Listeners
        addIncomeButton.setOnClickListener(addIncomeButtonListener);
        addExpenseButton.setOnClickListener(addExpenseButtonListener);
    }

    private View.OnClickListener addIncomeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Double income = Double.parseDouble(incomeEditText.getText().toString());
            String description = transactionDescription.getText().toString();

            DB.addIncome(income, description);

            // Erase content after every successful transaction
            incomeEditText.setText("");
            transactionDescription.setText("");
        }
    };

    private View.OnClickListener addExpenseButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Double expense = Double.parseDouble(expenseEditText.getText().toString());
            String description = transactionDescription.getText().toString();

            DB.addExpense(expense, description);

            // Erase content after every successful transaction
            expenseEditText.setText("");
            transactionDescription.setText("");
        }
    };
}
