package com.charlesplett.ims;

import static com.charlesplett.ims.SharedUtility.isValidDouble;
import static com.charlesplett.ims.SharedUtility.isValidInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller Class for ModifyPart. 
 * @author Charles Plett
 */
public class ModifyPartController implements Initializable {
    
    private static Part partToModify;
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
    private TextField companyField;
    @FXML
    private TextField machineField;
    @FXML
    private Label machineLabel;
    @FXML
    private Label companyLabel;
    @FXML
    private RadioButton isInHouse;
    @FXML
    private RadioButton isOutsourced;
    @FXML
    private Button cancelButton;
    
    /**
     * OnAction method to save the Part. Updates Part in inventory after validation. 
     */
    @FXML
    public void save(){
        int id = SharedUtility.myParseInt(idField.getText());
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
        double cost = SharedUtility.myParseDouble(costField.getText());
        int inv = SharedUtility.myParseInt(invField.getText());
        int min = SharedUtility.myParseInt(minField.getText());
        int max = SharedUtility.myParseInt(maxField.getText());
        if (name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - Missing Name");
            alert.setContentText("A part is required to have a name.");
            alert.showAndWait();
            return;
        } else if (min > inv || inv > max){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - Invalid Inventory");
            alert.setContentText("Part inventory must be between min and max.");
            alert.showAndWait();
            return;
        }
        if (isInHouse.isSelected()) {
            int machineId = SharedUtility.myParseInt(machineField.getText());
            InHouse part = new InHouse(id, name, cost, inv, min, max, machineId);
            // Add new Part to Inventory
            Inventory.addPart(part);
        } else {
            String companyName = companyField.getText();
            Outsourced part = new Outsourced(id, name, cost, inv, min, max, companyName); 
            // Add new Part to Inventory
            Inventory.addPart(part);
        }
        // Delete old Part
        Inventory.deletePart(partToModify);
        exit();
    }
    
    /**
     * Method to hide Company field and Label. Shows Machine field and label.
     */
    @FXML
    private void hideCompany(){
        machineLabel.setVisible(true);
        companyLabel.setVisible(false);
        machineField.setVisible(true);
        companyField.setVisible(false);
    }
    
    /**
     * Method to hide Machine field and Label. Shows Company field and label.
     */
    @FXML
    private void hideMachine(){
        companyLabel.setVisible(true);
        machineLabel.setVisible(false);
        companyField.setVisible(true);
        machineField.setVisible(false);
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
     * Sets the Part to Modify. 
     * @param partToModify The Part to modify. 
     */
    public static void setPartToModify(Part partToModify) {
        ModifyPartController.partToModify = partToModify;
    }
    
    /**
     * Method called on Init. Initializes the form fields. 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Init Fields
        idField.setText(String.valueOf(partToModify.getId()));
        nameField.setText(partToModify.getName());
        invField.setText(String.valueOf(partToModify.getStock()));
        costField.setText(String.valueOf(partToModify.getPrice()));
        maxField.setText(String.valueOf(partToModify.getMax()));
        minField.setText(String.valueOf(partToModify.getMin()));
        
        if(partToModify.getClass().getName().contains("ims.Outsourced")){
            // Part is Outsourced
            isOutsourced.setSelected(true);
            hideMachine(); // hide machine field
            Outsourced outsourcedPart = (Outsourced) partToModify;
            companyField.setText(outsourcedPart.getCompanyName());
        } else {
            // Part is In House
            isInHouse.setSelected(true);
            hideCompany(); // hide company field
            InHouse inHousePart = (InHouse) partToModify;
            machineField.setText(String.valueOf(inHousePart.getMachineId()));
        }
    }    
}
