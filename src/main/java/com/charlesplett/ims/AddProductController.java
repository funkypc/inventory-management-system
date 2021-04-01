package com.charlesplett.ims;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static com.charlesplett.ims.SharedUtility.*;

/**
 * Controller Class for AddProduct. 
 * @author Charles Plett
 */
public class AddProductController implements Initializable {

    private static ArrayList<Part> partSearchResults;
    @FXML
    private TextField partSearchField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField invField;
    @FXML
    private TextField costField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableView<Part> productPartTableView;
    @FXML
    private TableColumn<Part, String> partId;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, String> partStock;
    @FXML
    private TableColumn<Object, Double> partPrice;
    @FXML
    private TableColumn<Part, String> productPartId;
    @FXML
    private TableColumn<Part, String> productPartName;
    @FXML
    private TableColumn<Part, String> productPartStock;
    @FXML
    private TableColumn<Object, Double> productPartPrice;
    @FXML
    private Button cancelButton;
    
    /**
     * OnAction method to search for a Part. Search is done by Name or ID.
     */
    @FXML
    private void search(){
        String name = partSearchField.getText();
        partSearchResults = new ArrayList<>(Inventory.lookupPart(name));
        Part part = Inventory.lookupPart(myParseInt(partSearchField.getText()));
        if (part != null){
           partSearchResults.add(part); 
        }
        partTableView.getItems().setAll(partSearchResults);
    }
    
    /**
     * OnAction method to save a new Product. Adds new product to inventory after validation.
     */
    @FXML
    private void save(){
        int id = Product.getNextId();
        String name = nameField.getText();
        if(!isValidInt(invField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - Invalid Entry");
            alert.setContentText("Inventory must be a number!");
            alert.showAndWait();
            return;
        }
        if(!isValidDouble(costField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - Invalid Entry");
            alert.setContentText("Cost must be a number!");
            alert.showAndWait();
            return;
        }
        if(!isValidInt(minField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - Invalid Entry");
            alert.setContentText("Min must be a number!");
            alert.showAndWait();
            return;
        }
        if(!isValidInt(maxField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - Invalid Entry");
            alert.setContentText("Max must be a number!");
            alert.showAndWait();
            return;
        }
        double cost = myParseDouble(costField.getText());
        int inv = myParseInt(invField.getText());
        int min = myParseInt(minField.getText());
        int max = myParseInt(maxField.getText());
        if (name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - Missing Name");
            alert.setContentText("A product is required to have a name.");
            alert.showAndWait();
            return;
        } else if (min > inv || inv > max){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - Invalid Inventory");
            alert.setContentText("Product inventory must be between min and max.");
            alert.showAndWait();
            return;
        }
        Product newProduct = new Product(id, name, cost, inv, min, max);
        for(Part part:productPartTableView.getItems()){
            newProduct.addAssociatedPart(part);
        }
        Inventory.addProduct(newProduct);
        exit();
    }
    
    /**
     * OnAction method to add Part to product. Adds the selected part to the Product.
     */
    @FXML
    private void addPart(){
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if(selectedPart==null){
            return;
        }
        // Add part to productPartTable
        productPartTableView.getItems().add(selectedPart);
    }
    
    /**
     * OnAction method to remove a Part from the product. Removes the selected part from the Product.
     */
    @FXML
    private void removePart(){
        Part selectedPart = productPartTableView.getSelectionModel().getSelectedItem();
        if(selectedPart==null){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal");
        alert.setContentText("Are you sure you want to remove this part?");
        alert.showAndWait();
        if(alert.getResult().toString().contains("CANCEL_CLOSE")){
            return;
        }
        // Remove part from productPartTable
        productPartTableView.getItems().remove(selectedPart);
    }
    
    /**
     * OnAction method to exit and close the window. Requests confirmation.
     */
    @FXML
    private void cancel(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setContentText("Are you sure you want to leave? All changes will be lost.");
        alert.showAndWait();
        if(alert.getResult().toString().contains("CANCEL_CLOSE")){
            return;
        }
        exit();
    }

    /**
     * Exit method called to close the window.
     */
    private void exit(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
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
        // Init Product-Parts Table
        productPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Populate Tables
        partTableView.getItems().setAll(Inventory.getAllParts());
        // Format price to display as currency
        toCurrency(partPrice);
        toCurrency(productPartPrice);
        // Populate ID
        idField.setText(String.valueOf(Product.getNextId()));
    }    
    
}
