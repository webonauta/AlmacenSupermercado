/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnMenuClientes(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/VistaClientes.fxml"));
            
            Parent root = loader.load();
            
            VistaMenuController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
    }
    
    @FXML
    private void btnMenuProductos(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/VistaProductos.fxml"));
            
            Parent root = loader.load();
            
            VistaMenuController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
    }
    
    @FXML
    private void btnMenuProveedores(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/VistaProovedores.fxml"));
            
            Parent root = loader.load();
            
            VistaMenuController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
    }
    
}
