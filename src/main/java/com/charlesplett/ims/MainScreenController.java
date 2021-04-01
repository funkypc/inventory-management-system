package com.charlesplett.ims;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static com.charlesplett.ims.SharedUtility.*;

/**
 * Controller class for MainScreen. 
 * @author Charles Plett
 */
public class MainScreenController implements Initializable {
    
    private static ArrayList<Part> partSearchResults;
    private static ArrayList<Product> productSearchResults;
    @FXML
    private TextField partSearchField;
    @FXML
    private TextField productSearchField;
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, String> productStock;
    @FXML
    private TableColumn<Object, Double> productPrice;
    @FXML
    private TableColumn<Part, String> partId;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, String> partStock;
    @FXML
    private TableColumn<Object, Double> partPrice;
    @FXML
    private Button exitButton;
    
    /**
     * OnAction method to search for a part or product. Search is done by Name or ID.
     */
    @FXML
    private void search(ActionEvent event){
        String eventString = event.toString();
        if (eventString.contains("partSearchButton")){
            String name = partSearchField.getText();
            partSearchResults = new ArrayList<>(Inventory.lookupPart(name));
            Part part = Inventory.lookupPart(myParseInt(partSearchField.getText()));
            if (part != null){
               partSearchResults.add(part); 
            }
            partTableView.getItems().setAll(partSearchResults);
            
        } else if (eventString.contains("productSearchButton")){
            String name = productSearchField.getText();
            productSearchResults = new ArrayList<>(Inventory.lookupProduct(name));
            Product product = Inventory.lookupProduct(myParseInt(productSearchField.getText()));
            if (product != null){
               productSearchResults.add(product); 
            }
            productTableView.getItems().setAll(productSearchResults);
        }
    }
    
    /**
     * OnAction method to delete the selected Part or Product. Asks for confirmation.
     */
    @FXML
    private void delete(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete this item?");
        alert.showAndWait();
        if(alert.getResult().toString().contains("CANCEL_CLOSE")){
            return;
        }
        String eventString = event.toString();
        if (eventString.contains("partDeleteButton")){
            Inventory.deletePart(partTableView.getSelectionModel().getSelectedItem());
            // repopulate table
            partTableView.getItems().setAll(Inventory.getAllParts());
        } else if (eventString.contains("productDeleteButton")){
            Product product = productTableView.getSelectionModel().getSelectedItem();
            // Check if prdouct has associated parts
            if(!product.getAllAssociatedParts().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error - Cannot Delete");
                alert.setContentText("You must remove all parts from a product before deleting!");
                alert.showAndWait();
                return;
            }
            Inventory.deleteProduct(product);
            // repopulate table
            productTableView.getItems().setAll(Inventory.getAllProducts());
        }
    }
    
    /**
     * Exit method called to close the window.
     */
    @FXML
    private void exit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
   
    /**
     * OnAction method to add Part. Opens addPart window.
     */
    @FXML
    private void addPart(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
            showChildWindow(root, "IMS - Add Product", 386, 332);
            // Refresh Part list after returning
            partTableView.getItems().setAll(Inventory.getAllParts());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * OnAction method to add Product. Opens addProduct window.
     */
    @FXML
    private void addProduct(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
            showChildWindow(root, "IMS - Add Product", 774, 366);
            // Refresh Product list after returning
            productTableView.getItems().setAll(Inventory.getAllProducts());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * OnAction method to modify the selected Part. Opens the modifyPart window on the selected Part if a Part is selected.
     */
    @FXML
    private void modifyPart(){
        // Get selected Part
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if(selectedPart==null){
            return;
        }
        // Pass selected Part to child window
        ModifyPartController.setPartToModify(selectedPart);
        try{
            Parent root = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
            showChildWindow(root, "IMS - Add Product", 386, 332);
            // Refresh Part list after returning
            partTableView.getItems().setAll(Inventory.getAllParts());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * OnAction method to modify the selected Product. Opens the modifyProduct window on the selected Product if a Product is selected.
     */
    @FXML
    private void modifyProduct(){
        // Get selected Product
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if(selectedProduct==null){
            return;
        }
        // Pass selected Product to child window
        ModifyProductController.setProductToModify(selectedProduct);
        try{
            Parent root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
            showChildWindow(root, "IMS - Add Product", 774, 366);
            // Refresh Product list after returning
            productTableView.getItems().setAll(Inventory.getAllProducts());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
         
    /**
     * Method called on init. Initializes the Part and Product tables.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Init Parts Table
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Init Products Table
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Populate Tables
        partTableView.getItems().setAll(Inventory.getAllParts());
        productTableView.getItems().setAll(Inventory.getAllProducts());
        // Format price to display as currency
        toCurrency(partPrice);
        toCurrency(productPrice);
    }
}
