package com.charlesplett.ims;

/** InHouse is the public class for all InHouse parts. */
public class InHouse extends Part {

    private int machineId;

    // Default Constructor

    /**
     * Class constructor.
     * @param id The id of the new part. Usually set with getNextId().
     * @param name The name of the new part.
     * @param price The price as double of the new part.
     * @param stock The stock or inventory level of the part.
     * @param min The minimum inventory level. 
     * @param max The maximum inventory level.
     * @param machineId The machineId associated with the InHouse part.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    // Getters and Setters

    /**
     * Gets the machineId of the InHouse Part. 
     * @return Returns machineId as int.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets the machineId of the InHouse Part.
     * @param machineId The int to set as machineId.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
}
