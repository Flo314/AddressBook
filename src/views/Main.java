/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.HomeController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author florent losada
 */
public class Main extends Application {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
