package com.charlesplett.ims;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/** Base application class for Inventory Management System.
 * @author Charles Plett
 */
public class App extends Application {
    
    private static Scene scene;
    
    /** Creates the stage and scene of the Application. Invoked on App run after main.
     * @param stage The stage to show.
     * @throws java.io.IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainScreen"), 1030, 500);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("IMS256.png")));
        stage.setTitle("IMS - Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads the FXML file for the scene. 
     * @param fxml The name of the fxml file to load.
     * @return Returns fxmlLoader if successful.
     * @throws IOException 
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * Main method to launch application. Initializes sample data. Invoked on App run.
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        
        // Initial sample data
        // Create new sample parts and products
        InHouse part1 = new InHouse(AddPartController.getNextId(), "Part One", 12.99, 1, 1, 10, 8789);
        Outsourced part2 = new Outsourced(AddPartController.getNextId(), "Part Two", 29.97, 1, 1, 9, "ABC Company");
        Product product1 = new Product(Product.getNextId(), "Product One", 101.99, 1, 1, 10);
        Product product2 = new Product(Product.getNextId(), "Product Two", 69.99, 1, 1, 10);
        // Add parts to sample products
        product1.addAssociatedPart(part1);
        product2.addAssociatedPart(part1);
        product2.addAssociatedPart(part2);
        // Add sample parts and products to Inventory
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
                
        launch(args);
    }
}
