package com.cragtowercreations.moneyhandler;

public class TransactionItems {

    private int id;
    private String income;
    private String expense;
    private String desc;

    public TransactionItems(int id, String income, String expense, String desc) {
        this.id = id;
        this.income = income;
        this.expense = expense;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
