package com.cragtowercreations.moneyhandler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {

    // Creating constant variables for database

    // Variable for database name
    private static final String DB_NAME = "monthly_budget.db";

    // Variable for database version #
    private static final int DB_VERSION = 2;

    // Variable for table name
    private static final String TABLE_NAME = "monetaryTransactions";

    // Variable for id column name
    private static final String ID_COL = "id";

    // Variable for income column name
    private static final String INCOME_COL = "incomeCol";

    // Variable for expense column name
    private static final String EXPENSE_COL = "expenseCol";

    // Variable for description column name
    private static final String DESC_COL = "descriptionCol";

    // Creating constructor
    public AppDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Creates database and accompanying tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating SQLite query variable that sets table and column names
        // While also setting column data types
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + INCOME_COL + " DOUBLE, "
                + EXPENSE_COL + " DOUBLE, "
                + DESC_COL + " TEXT)";

        // Executes SQL query to create table
        db.execSQL(query);
    }

    // Upgrades database to new version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Checks if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addIncome (Double income, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INCOME_COL, income);
        values.put(DESC_COL, description);

        long incomeId = db.insert(TABLE_NAME, null, values);
    }

    public void addExpense (Double expense, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EXPENSE_COL, expense);
        values.put(DESC_COL, description);

        long expenseId = db.insert(TABLE_NAME, null, values);
    }

    public void deleteIncome (int incomeId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String id = Integer.toString(incomeId);

        int rowsDeleted = db.delete(TABLE_NAME, ID_COL + " = ?", new String[] {id});
    }

    public void deleteExpense (int expenseId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String id = Integer.toString(expenseId);

        int rowsDeleted = db.delete(TABLE_NAME, ID_COL + " = ?", new String[] {id});
    }

    public List<Double> getAllIncomes () {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Double> incomeList = new ArrayList<>();

        String sqlQuery = "SELECT " + INCOME_COL + " FROM " + TABLE_NAME + " WHERE " + INCOME_COL + " IS NOT NULL";
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {
                double income = cursor.getDouble(0);
                incomeList.add(income);
            } while (cursor.moveToNext());
        }

        return incomeList;
    }

    public List<Double> getAllExpenses () {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Double> expenseList = new ArrayList<>();

        String sqlQuery = "SELECT " + EXPENSE_COL + " FROM " + TABLE_NAME + " WHERE " + EXPENSE_COL + " IS NOT NULL";
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {
                double expense = cursor.getDouble(0);
                expenseList.add(expense);
            } while (cursor.moveToNext());
        }

        return expenseList;
    }


}
