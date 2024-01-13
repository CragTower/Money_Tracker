package com.cragtowercreations.moneyhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddTransactionActivity extends AppCompatActivity {

    // Declaring variables
    TextView transactionDescription;
    EditText incomeEditText;
    EditText expenseEditText;
    Button addIncomeButton;
    Button addExpenseButton;
    AppDatabase DB;

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
