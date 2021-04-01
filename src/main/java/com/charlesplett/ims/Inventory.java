package com.charlesplett.ims;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * The public class for all Inventory objects. 
 * @author Charles Plett
 */
public final class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a new Part to Inventory. 
     * @param newPart The Part to add to Inventory. 
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    /**
     * Adds a new Product to Inventory. 
     * @param newProduct The Product to add to Inventory. 
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    /**
     * Lookup Part in Inventory by ID. 
     * @param partId The ID as int of the part to lookup. 
     * @return Returns the Part on success. Else returns null. 
     */
    public static Part lookupPart(int partId) {
        for(Part part:allParts){
            if (partId == part.getId()){
                return part;
            }
        }
        return null;
    }
    
    /**
     * Lookup Product in Inventory by ID. 
     * @param productId The ID as int of the product to lookup. 
     * @return Returns the Product on success. Else returns null. 
     */
    public static Product lookupProduct(int productId) {
        for(Product product:allProducts){
            if (productId == product.getId()){
                return product;
            }
        }
        return null;
    }
    
    /**
     * Lookup Part in Inventory by Name. 
     * @param partName The Name as String of the part. 
     * @return Returns an ObservableList of Part that match partName.  
     */
    public static ObservableList<Part> lookupPart(String partName) {
        FilteredList<Part> filteredPartList = new FilteredList<>(allParts, p -> true);
        String lowerPartName = partName.toLowerCase();
        filteredPartList.setPredicate(part -> part.getName().toLowerCase().contains(lowerPartName));
        return filteredPartList;
    }
    
    /**
     * Lookup Product in Inventory by Name. 
     * @param productName The Name as String of the product. 
     * @return Returns an ObservableList of Product that match productName. 
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        FilteredList<Product> filteredProductList = new FilteredList<>(allProducts, p -> true);
        String lowerProductName = productName.toLowerCase();
        filteredProductList.setPredicate(product -> product.getName().toLowerCase().contains(lowerProductName));
        return filteredProductList;
    }
    
    /**
     * Updates an existing Part in Inventory. 
     * @param index The index of the part to update as int. 
     * @param selectedPart The Part to update. 
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    
    /**
     * Updates an existing Product in Inventory. 
     * @param index The index of the product to update as int.
     * @param selectedProduct The Product to update. 
     */
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    
    /**
     * Deletes a Part from Inventory. 
     * @param selectedPart The Part to delete. 
     * @return Returns true on success. False on failure.
     */
    public static boolean deletePart(Part selectedPart){
        if (allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }
    
    /**
     * Deletes a Product from Inventory. 
     * @param selectedProduct The Product to delete.
     * @return Returns true on success. False on Failure.
     */
    public static boolean deleteProduct(Product selectedProduct){
        if (allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        }
        return false;
    }
    
    /**
     * Gets all Parts in Inventory. 
     * @return Returns an ObservableList with all Parts in Inventory.
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    /**
     * Gets all Products in Inventory. 
     * @return Returns an ObservableList with all Products in Inventory.
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
