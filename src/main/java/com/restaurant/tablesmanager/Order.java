package com.restaurant.tablesmanager;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> orderList;
    private Waiter waiterAssigned;
    private boolean isCompleted = false;

    private Table table;

    public Order(Waiter waiterAssigned, Table table){
        this.orderList = new ArrayList<>();
        this.waiterAssigned = waiterAssigned;
        this.table = table;
    }

    public void changeStatusCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    public void addItemToOrder(OrderItem orderItem){
        this.orderList.add(orderItem);
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public List<OrderItem> getOrderList(){
        return this.orderList;
    }

    public Table getTable() {
        return table;
    }

    public Waiter getWaiterAssigned() {
        return waiterAssigned;
    }
}
