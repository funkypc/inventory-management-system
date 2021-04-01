package com.charlesplett.ims;

/** Outsourced is the public class for all Outsourced parts. */
public class Outsourced extends Part{

    private String companyName;

    // Default Constructor

    /**
     * Class constructor.
     * @param id The id of the new part. Usually set with getNextId().
     * @param name The name of the new part.
     * @param price The price as double of the new part.
     * @param stock The stock or inventory level of the part.
     * @param min The minimum inventory level. 
     * @param max The maximum inventory level.
     * @param companyName The companyName associated with the Outsourced part.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    // Getters and Setters

    /**
     * Gets the companyName of the Outsourced Part. 
     * @return Returns companyName as String.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the companyName of the Outsourced Part. 
     * @param companyName The String to set as companyName.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
