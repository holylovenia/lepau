package com.kami.lepau.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Order contains a list of ordered items from Lepau.
 * The class is made Serializable for easier data passing between Intents.
 *
 * @see <a href="https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android">Intent Passing a Serializable Data Class</a>
 */
public class Order implements Serializable {
    private ArrayList<OrderItem> orders;
    private String date;

    public Order() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        date = simpleDateFormat.format(new Date());
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

    public int getTotal() {
        int total = 0;
        for (int i = 0; i < orders.size(); i++) {
            total += orders.get(i).getTotalPrice();
        }
        return total;
    }

    public String getOrdersInString() {
        StringBuilder ordersInString = new StringBuilder();
        for (int i = 0; i < orders.size(); i++) {
            ordersInString.append(orders.get(i).getQuantity()).append(" ").append(orders.get(i).getItemName()).append("\n");
        }
        return ordersInString.toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        this.date = simpleDateFormat.format(date);
    }
}
