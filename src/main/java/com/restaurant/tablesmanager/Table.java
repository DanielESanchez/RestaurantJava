package com.restaurant.tablesmanager;

public class Table {
    private int tableNumber;
    private boolean isEmpty;

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void changeStatusTable() {
        isEmpty = !isEmpty;
    }
}

