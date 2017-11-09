package com.kami.lepau.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * OrderItem contains an item that could be ordered in Lepau.
 * The class is made Serializable for easier data passing between Intents.
 * @see <a href="https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android">Intent Passing a Serializable Data Class</a>
 */
public class OrderItem implements Serializable {

    private String itemName = "";
    private String itemDescription = "";
    private int pricePerItem = 0;
    private int quantity = 0;
    private int imageResource = 0;
    private SpecialOrderItem specialOrderItem = null;

    /**
     * Default constructor for OrderItem
     */
    public OrderItem() {}

    /**
     * Constructor for OrderItem
     * @param itemName the item's name
     * @param itemDescription the item's description
     * @param pricePerItem the item's price
     * @param quantity the amount of item
     */
    public OrderItem(String itemName, String itemDescription, int pricePerItem, int quantity, int imageResource) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.pricePerItem = pricePerItem;
        this.quantity = quantity;
        this.imageResource = imageResource;
    }

    public OrderItem(boolean isSpecialOrder) {
        this.itemName = "Custom Fried Rice";
        this.itemDescription = "Lepau's specialty! Make your own fried rice.";
        this.pricePerItem = 0;
        this.quantity = 0;
        this.specialOrderItem = new SpecialOrderItem();
        this.imageResource = -999;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getPricePerItem() {
        return pricePerItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImageResource() {
        return imageResource;
    }

    public SpecialOrderItem getSpecialOrderItem() {
        return specialOrderItem;
    }

    /**
     * Returns total price of this order
     * @return total price based on price per item and current quantity
     */
    public int getTotalPrice() {
        return pricePerItem * quantity;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setPricePerItem(int pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    private class SpecialOrderItem implements Serializable {

        private String riceType;
        private String size;
        private int spicinessLevel;
        private ArrayList<String> toppingList;

        public String getRiceType() {
            return riceType;
        }

        public String getSize() {
            return size;
        }

        public int getSpicinessLevel() {
            return spicinessLevel;
        }

        public ArrayList<String> getToppingList() {
            return toppingList;
        }

        public void setRiceType(String riceType) {
            this.riceType = riceType;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public void setSpicinessLevel(int spicinessLevel) {
            this.spicinessLevel = spicinessLevel;
        }

        public void setToppingList(ArrayList<String> toppingList) {
            this.toppingList = toppingList;
        }
    }
}
