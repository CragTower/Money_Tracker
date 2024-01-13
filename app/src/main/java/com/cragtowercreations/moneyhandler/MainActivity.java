package com.cragtowercreations.moneyhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    }

    private View.OnClickListener addTransactionButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), AddTransactionActivity.class);
            startActivity(intent);
        }
    };
}