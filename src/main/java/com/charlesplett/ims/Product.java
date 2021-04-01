package com.charlesplett.ims;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Product is the base class for all products. */
public class Product {

    private ObservableList<Part> AssociatedParts  = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private static int nextId = 1000;

    // Constructor

    /**
     * Class constructor. Invoking will increment nextId.
     * @param id The id of the new product as int. Usually set with getNextId().
     * @param name The name of the new product as String.
     * @param price The price of the new product as double.
     * @param stock The stock or inventory level of the product as int.
     * @param min The minimum inventory level as int. 
     * @param max The maximum inventory level as int.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.nextId ++; // Increment nextId
    }

    // Getters and Setters

    /**
     * Gets the id of the Product. 
     * @return Returns id as int.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the Product. 
     * @param id The int to set as id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the Product. 
     * @return Returns name as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Product. 
     * @param name The String to set as name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the Product. 
     * @return Returns price as double.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the Product. 
     * @param price The double to set as price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the stock of the Product. 
     * @return Returns stock as int.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock of the Product. 
     * @param stock The int to set as stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the min of the Product. 
     * @return Returns min as int.
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the min of the Product. 
     * @param min The int to set as min.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets the max of the Product. 
     * @return Returns max as int.
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the max of the Product. 
     * @param max The int to set as max.
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * Gets the nextId. The next available ID for instantiating the class. 
     * @return Returns nextId as int.
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Gets all associated parts of the Product. 
     * @return Returns ObservableList of parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return AssociatedParts;
    }

    /**
     * Adds a Part associated with Product. 
     * @param part The Part to add as associatedPart.
     */
    public void addAssociatedPart(Part part) {
        this.AssociatedParts.add(part);
    }
    
    /**
     * Removes associated part from Product. 
     * @param selectedAspart The Part to remove from the Product.
     * @return Returns true on success.
     */
    public boolean deleteAssociatedPart(Part selectedAspart) {
        if (this.AssociatedParts.contains(selectedAspart)){
            this.AssociatedParts.remove(selectedAspart);
            return true;
        }
        return false;
    }
}
