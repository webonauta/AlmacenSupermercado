/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import controller.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaMenuController implements Initializable {

    @FXML
    private Button btnMenuClientes;
    @FXML
    private Button btnMenuProductos;
    @FXML
    private Button btnMenuProveedores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void clientes(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaAgregarClientes.fxml");
    }

    @FXML
    private void productos(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaAgregarProductos.fxml");
    }

    @FXML
    private void proveedores(ActionEvent event) throws IOException {
        lanzarSiguienteVentana("VistaAgregarProveedores.fxml");
    }
    
    private void lanzarSiguienteVentana(String vista) throws IOException{
            FXMLLoader loader = new FXMLLoader();//cardo la vista
            loader.setLocation(Main.class.getResource("/View/"+vista));
            //cargo la ventana
            AnchorPane ventana = (AnchorPane) loader.load();
            Scene scene = new Scene(ventana);//panel
            //seteo ka scene y la muestro
            Stage primaryStage = new Stage();//jframe
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            ((Stage) (btnMenuClientes.getScene().getWindow())).close();
    }
    
}
