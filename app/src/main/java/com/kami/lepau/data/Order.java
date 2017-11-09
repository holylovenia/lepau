package com.kami.lepau.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * MenuActivity contains a list of ordered items from Lepau.
 * The class is made Serializable for easier data passing between Intents.
 * @see <a href="https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android">Intent Passing a Serializable Data Class</a>
 */
public class Order implements Serializable{
    private ArrayList<OrderItem> orders;

    public Order() {
        orders = new ArrayList<>();
    }

    public OrderItem getOrder(int index) {
        return orders.get(index);
    }

    public void addOrder(OrderItem order) {
        orders.add(order);
    }

    public void deleteOrder(int index) {
        orders.remove(index);
    }

    public ArrayList<OrderItem> getAllOrders() {
        return orders;
    }
}
