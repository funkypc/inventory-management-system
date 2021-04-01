package com.charlesplett.ims;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.text.NumberFormat;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/** Public class of shared static methods. */
public final class SharedUtility {
    
    /** Parses String to int. 
    @param strNumber The String to convert to an int. 
    @return Returns the parsed int if successful. Returns 0 on failure.
    */
    public static int myParseInt(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return parseInt(strNumber);
            } catch(Exception e) {
                return 0;
            }
        }
        else return 0;
    }
    
    /** Parses String to Double. 
    @param strNumber The String to convert to Double
    @return Returns the parsed Double if successful. Returns 0 on failure.
    */
    public static double myParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return parseDouble(strNumber);
            } catch(Exception e) {
                return 0;
            }
        }
        else return 0;
    }
    
    /**
     * Checks if a String can be parsed as a Double. 
     * @param strNumber The String to check.
     * @return Result as boolean.
     */
    public static boolean isValidDouble(String strNumber){
        if (strNumber != null && strNumber.length() > 0) {
            try {
                parseDouble(strNumber);
                return true;
            } catch(Exception e) {
                return false;
            }
        }
        else return true;
    }
    
    /**
     * Checks if a String can be parsed as an int. 
     * @param strNumber The String to check.
     * @return Result as boolean.
     */
    public static boolean isValidInt(String strNumber){
        if (strNumber != null && strNumber.length() > 0) {
            try {
                parseInt(strNumber);
                return true;
            } catch(Exception e) {
                return false;
            }
        }
        else return true;
    }
    
    /** Formats TableColumn containing Double to display as currency. 
    The value of all cells in column will be displayed in the currency.
    @param column The TableColumn to be displayed as currency.
    */
    public static void toCurrency(TableColumn <Object,Double> column){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        column.setCellFactory(c -> new TableCell<Object, Double>() {
            @Override
            protected void updateItem(Double cost, boolean isEmpty) {
                super.updateItem(cost, isEmpty);
                if (isEmpty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(cost));
                }
            }
        });
    }

    /** Opens a child window. 
    The value of all cells in column will be displayed in the currency. 
    @param root The root from FXMLLoader. 
    @param title The title for the child window. 
    @param x The width of the child window, in pixels. 
    @param y The height of the child window, in pixels. 
    */    
    public static void showChildWindow(Parent root, String title, int x, int y){
        Stage stage = new Stage();
        // Set the application icon
        stage.getIcons().add(new Image(App.class.getResourceAsStream("IMS256.png")));
        stage.setTitle(title);
        Scene scene = new Scene(root, x, y);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
